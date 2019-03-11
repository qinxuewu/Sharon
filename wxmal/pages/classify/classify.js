// pages/classify/classify.js

const app = getApp()
const util = require('../../utils/util.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    StatusBar: app.globalData.StatusBar,
    CustomBar: app.globalData.CustomBar,
    Custom: app.globalData.Custom,
    TabCur: 0,
    VerticalNavTop: 0,
    imgUrls: [],
    imgViewPath: util.imgViewPath,
    list: []
  }, 
  tabSelect(e) {
    this.setData({
      TabCur: e.currentTarget.dataset.id,
      VerticalNavTop: (e.currentTarget.dataset.id - 1) * 50
    })
  },
  VerticalMain(e) {
    console.log(e.detail);
  },
  onLoad: function (options) {
    this.getBannerList();
  },
  onShow: function () {
    this.getList();
  },
  onShareAppMessage: function () {
    return {
      // title: 'Sharon的生活馆',
      path: '/pages/classify/classify'
    }
  },
  getBannerList: function () {
    var that = this;
    wx.request({
      url: util.basePath + 'pro/bannerType?type=2',
      dataType: 'json',
      success(res) {
        if (res.statusCode == 200 && res.data.code == 0) {
          // console.log(res.data.data)
          that.setData({ imgUrls: res.data.data })
        }

      }
    })
  },
  getList: function () {
    var that = this;
    this.setData({ loadModal: true })

    wx.request({
      url: util.basePath + 'pro/categoryList',
      dataType: 'json',
      success(res) {
        if (res.statusCode == 200 && res.data.code == 0) {
          console.log(res.data.data[0].proList);
          that.setData({list: res.data.data });
          setTimeout(() => { that.setData({ loadModal: false }) }, 500);
        } else {
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