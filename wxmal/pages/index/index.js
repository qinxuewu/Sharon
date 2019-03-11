//index.js
//获取应用实例
const app = getApp()
const util = require('../../utils/util.js');

Page({
  data: {
    StatusBar: app.globalData.StatusBar,
    CustomBar: app.globalData.CustomBar,
    imgUrls:[],
    imgViewPath:util.imgViewPath,
    list:[],
    imgWidth: 0, 
    imgHeight: 0

  },
  onLoad: function () {
 
    this.getBannerList();
 
  }, 
  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
      this.getList();
  },  
   /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
    // // 显示顶部刷新图标
    // wx.showNavigationBarLoading();
    this.getBannerList();
    this.getList();

    setTimeout(function () {
      // 隐藏导航栏加载框
      // wx.hideNavigationBarLoading();
      //停止当前页面下拉刷新。
      wx.stopPullDownRefresh()
    }, 1500)

  },
  onShareAppMessage: function () {
    return {
      // title: 'Sharon的生活馆',
      path: '/pages/index/index'
    }
  },
  getBannerList:function(){
    var that = this;
      wx.request({
        url: util.basePath + 'pro/bannerType?type=1',
        dataType: 'json',
        success(res) {
            if (res.statusCode == 200 && res.data.code==0) {
                console.log('广告图获取.....');
                 console.log(res.data.data)
                that.setData({imgUrls: res.data.data})
            }

         }
      })
  },
 getList:function(){
   var that = this;
   this.setData({ loadModal: true })
 
   wx.request({
     url: util.basePath + 'pro/list',
     dataType: 'json',
     success(res) {
       if (res.statusCode == 200 && res.data.code == 0) {
          console.log(res.data.data)
          that.setData({list: res.data.data })
          setTimeout(() => { that.setData({ loadModal: false })},500)
       }else{
          that.setData({ loadModal: false })
          wx.showToast({ title: '数据异常', icon: "none" })
       }

     }
   })
 },
  details(e) {
    //详情页跳转
    console.log(e.currentTarget.id)
    wx.navigateTo({
      url: '../details/details?id=' + e.currentTarget.id
    })

  },
})
