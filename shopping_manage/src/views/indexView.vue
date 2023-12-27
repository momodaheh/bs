<template>
  <div class="all">
    <div class="header">
      <span style="margin-left:50px">电子国货商店后台管理系统</span>
    </div>
    <div class="main">
      <div class="left">
        <el-menu
          :router="true"
          background-color="#2f4158"
          text-color="#f1f1f1"
          :unique-opened="true"
        >
          <el-menu-item index="/homeView">
            <svg class="icon" aria-hidden="true">
              <use xlink:href="#icon-shouye"></use>
            </svg>
            报表
          </el-menu-item>
          <el-menu-item index="/userView">
            <svg class="icon" aria-hidden="true">
              <use xlink:href="#icon-yonghu"></use>
            </svg>
            用户管理
          </el-menu-item>
          <el-sub-menu index="1">
            <template #title>
              <svg class="icon" aria-hidden="true">
                <use xlink:href="#icon-shangpin"></use>
              </svg>
              <span>商品管理</span>
            </template>
            <el-menu-item index="/goodsList">商品列表</el-menu-item>
            <el-menu-item index="/goodsBrand">品牌管理</el-menu-item>
            <!-- <el-menu-item index="/goodsCategory">商品分类</el-menu-item> -->
            <el-menu-item index="/addGood">添加商品</el-menu-item>
          </el-sub-menu>
          <el-sub-menu index="2">
            <template #title>
              <svg class="icon" aria-hidden="true">
                <use xlink:href="#icon-dingdan"></use>
              </svg>
              <span>订单管理</span>
            </template>
            <el-menu-item index="/orderList">订单列表</el-menu-item>
            <el-menu-item index="/orderDelivery">待发货</el-menu-item>
          </el-sub-menu>
          <el-menu-item index="/commentView">
            <svg class="icon" aria-hidden="true">
              <use xlink:href="#icon-pinglun1"></use>
            </svg>
            评论管理
          </el-menu-item>
        </el-menu>
      </div>
      <div class="right">
        <router-view> </router-view>
      </div>
    </div>
  </div>
</template>

<style lang="less" >
.all {
  width: 100%;
  height: 100%;
  position: absolute;
  display: flex;
  flex-direction: column;
}
.header {
  width: 100%;
  height: 50px;
  background-color: #333;
  color: #f1f1f1; /* 更改字体颜色 */
  display: flex;
  align-items: center; /* 垂直居中 */
}
.main {
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: row;
}
.left {
  background-color: #2f4158;
  color: #333;
  text-align: center;
  line-height: 200px;
  height: 100%;
  width: 200px;
  .el-sub-menu {
    .el-menu-item {
      background-color: #1f2d3e;
    }
  }
}

.right {
  background-color: #e9eef3;
  color: #333;
  height: 100%;
  width: 100%;
}
.icon {
  width: 35px;
  height: 35px;
  padding: 5px;
}
</style>
