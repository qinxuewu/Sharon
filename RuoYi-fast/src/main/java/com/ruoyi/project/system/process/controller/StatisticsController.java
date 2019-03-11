package com.ruoyi.project.system.process.controller;
import com.ruoyi.common.utils.CSVUtils;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.process.domain.Process;
import com.ruoyi.project.system.process.service.IProcessService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.text.SimpleDateFormat;

/**
 * 工分统计
 * @author qinxuewu
 * @version 1.00
 * @time  21/2/2019 下午 3:42
 * @email 870439570@qq.com
 */
@Controller
@RequestMapping("/system/statistics")
public class StatisticsController  extends BaseController {

    private String prefix = "system/statistics";
    @Autowired
    private IProcessService processService;

    @RequiresPermissions("system:statistics:view")
    @GetMapping()
    public String process()
    {
        return prefix + "/statistics";
    }



    /**
     * 统计工分列表
     */
    @RequiresPermissions("system:statistics:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Process process){
        startPage();
        List<Process> list = processService.selectProcessStatistics(process);
        return getDataTable(list);
    }

    /**
     * 导出工序列表
     */
    @RequiresPermissions("system:statistics:export")
    @GetMapping("/export")
    public void export(HttpServletRequest request, HttpServletResponse response) {
        String  employeeNo=request.getParameter("employeeNo");
        String  beginTime=request.getParameter("beginTime");
        String  endTime=request.getParameter("endTime");

        Process process=new Process();
        process.setEmployeeNo(employeeNo);
        Map<String, Object> params=new HashMap<>();
        params.put("beginTime",beginTime);
        params.put("endTime",endTime);
        process.setParams(params);

        List<Process> list = processService.selectProcessList(process);
        //csv表头
        String header = "工票号,工序号,员工号,工分,备注,创建日期";
        //下面 data里的key，可以说是数据库字段了
        String key = "ticketNo,processNo,employeeNo,score,remark,createTime";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String fileName = sdf.format(new Date()).toString() +employeeNo+ "工分统计清单.csv";
        List<Map<String,Object>> data = new ArrayList<Map<String,Object>>();
        list.forEach(i->{
            Map<String, Object> map=new HashMap<>(10);
            map.put("ticketNo",i.getTicketNo());
            map.put("processNo",i.getProcessNo());
            map.put("employeeNo",i.getEmployeeNo());
            map.put("score",i.getScore());
            map.put("remark",i.getRemark());
            map.put("createTime",DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss",i.getCreateTime()));
            data.add(map);
        });
        //从数据库加载 你的数据
        String content = CSVUtils.formatCsvData(data, header, key);
        try {
            CSVUtils.exportCsv(fileName, content,request, response);
            return;
        } catch (Exception e) {

        }

    }

}
