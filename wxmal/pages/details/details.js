import Poster from '../../miniprogram_dist/poster/poster';
const app = getApp();
const util = require('../../utils/util.js');
var id = 1;
var logo="";
var title="";
var price=0;


Page({
  data: {
    posterConfig: {},
    StatusBar: app.globalData.StatusBar,
    CustomBar: app.globalData.CustomBar,
  },
  onLoad: function (options) {
    var that = this;
    id = options.id;
    that.getDetails(id);

  },
  wxmlTagATap(e) {
    console.log(e);
  },
  onPullDownRefresh() {
    this.onLoad();
  },
  onPosterSuccess(e) {
    const { detail } = e;
    wx.previewImage({
      current: detail,
      urls: [detail]
    })
  },
  onPosterFail(err) {
    console.error(err);
  },

 /**
   * 异步生成海报
   */
  onCreateOtherPoster() {
    var jdConfig={
      width: 750,             //画布宽度
      height: 1280,           //画布高度
      backgroundColor: '#fff', //画布颜色
      debug: false,
      blocks: [
        { width: 690, height: 750, x: 30, y: 183, borderWidth: 2, borderColor: '#f0c2a0', borderRadius: 20 },
        { width: 634, height: 74, x: 59, y: 770, backgroundColor: '#fff', opacity: 0.5, zIndex: 100 },
      ],
      texts: [
        { x: 30, y: 70, baseLine: 'top', text: '发现一个好物，推荐给你呀', fontSize: 38, color: '#080808' },
        { x: 92, y: 810, fontSize: 38, baseLine: 'middle', text: this.title, width: 570, lineNum: 1, color: '#8d8d8d', zIndex: 200, },
        { x: 59, y: 895, baseLine: 'middle', text: [{ text: '¥'+this.price, fontSize: 36, color: '#ec1731', marginLeft: 30 }] },
        { x: 360, y: 1065, baseLine: 'top', text: '长按识别小程序码', fontSize: 38, color: '#080808', },
        { x: 360, y: 1123, baseLine: 'top', text: '超值好货一起拼', fontSize: 28, color: '#929292' },
      ],
      images: [
        {
          width: 634, height: 634, x: 59, y: 210,
          url: this.logo, //商品图
        },
        {
          width: 220, height: 220, x: 92, y: 1020,
          url: 'https://www.qinxuewu.club/profile/upload/xcxqrcode.jpg',  //二维码
        }
      ]
    }
    this.setData({ posterConfig: jdConfig}, () => {
      Poster.create(true);    // 入参：true为抹掉重新生成 
    });


  },   
   onShareAppMessage() {
     return {
      //  title: 'Sharon',
       path: '/pages/details/details?id=' + id
     }
  },
  showModal(e) {
    //二维码弹窗
    this.setData({
      modalName: e.currentTarget.dataset.target
    })
  }, 
  hideModal(e) {
    this.setData({
      modalName: null
    })
  }, 
  getDetails: function (id) {
    wx.request({
      url: util.basePath+'pro/details?id=' + id,
      success: res => {
        console.log(res);
        if (res.statusCode == 200 && res.data.code == 0) {
            wx.stopPullDownRefresh();

          this.logo = util.imgViewPath + res.data.product.logo;
          this.title = res.data.product.title;
          this.price = res.data.product.newprice

            this.setData({
              title: res.data.product.title,
              newprice: res.data.product.newprice,
              oldprice: res.data.product.oldprice,
              details: res.data.product.details,
              logo: res.data.product.logo
          });


        } else {
            wx.showToast({ title: '数据异常', icon: "none" })
        }
      }
    })
  },
  
/**
* 页面相关事件处理函数--监听用户下拉动作
*/
  onPullDownRefresh: function () {
    // // 显示顶部刷新图标
    // wx.showNavigationBarLoading();
    this.onLoad();
    setTimeout(function () {
      // 隐藏导航栏加载框
      // wx.hideNavigationBarLoading();
      //停止当前页面下拉刷新。
      wx.stopPullDownRefresh()
    }, 1500)

  },
  showQrcode:function(){
    wx.previewImage({
      urls: ['https://www.qinxuewu.club/profile/upload/qrcode.jpg'],
      current: 'https://www.qinxuewu.club/profile/upload/qrcode.jpg' // 当前显示图片的http链接      
    })
  }
})


