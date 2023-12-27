<template>
  <div class="spIndex">
    <div class="top">
      <div class="top_carousel">
        <el-carousel indicator-position="outside" height="300px" >
        <el-carousel-item >
          <el-image :src="require('@/assets/img/1.jpg')" fit="cover" />
        </el-carousel-item>
        <el-carousel-item >
          <el-image :src="require('@/assets/img/2.jpg')" fit="cover" />
        </el-carousel-item>
        <el-carousel-item >
          <el-image :src="require('@/assets/img/3.jpg')" fit="cover" />
        </el-carousel-item>
        <el-carousel-item >
          <el-image :src="require('@/assets/img/4.jpg')" fit="cover"/>
        </el-carousel-item>
      </el-carousel>
      </div>
      
    </div>
    
    <div class="middle">
      <div class="middleItem">
        <div class="item_top">
          <h3>手机</h3>
          <span>更多></span>
        </div>
        <el-row :gutter="20">
          <el-col 
            v-for="item in phoneList"
            :key="item.id"
            :span="6"
          >
            <el-card>
              <div class="item_card" @click="showDetail(item.id)" >
                <img :src="item.imgShow1">
              <span>{{item.goodsName}}</span>
              <span class="priceStyle">￥{{item.minprice}}</span>
              </div>
              
              
            </el-card>
          </el-col>
        </el-row>
      </div>

      <div class="middleItem" style="margin-top:30px">
        <div class="item_top">
          <h3>电脑</h3>
          <span>更多></span>
        </div>
        <el-row :gutter="20">
          <el-col 
            v-for="item in comList"
            :key="item.id"
            :span="6"
          >
            <el-card >
              <div class="item_card" @click="showDetail(item.id)">
                <img :src="item.imgShow1">
              <span>{{item.goodsName}}</span>
              <span class="priceStyle">￥{{item.minprice}}</span>
              </div>
              
              
            </el-card>
          </el-col>
        </el-row>
      </div>

      <div class="middleItem">
        <div class="item_top">
          <h3>手机</h3>
          <span>更多></span>
        </div>
        <el-row :gutter="20">
          <el-col 
            v-for="item in phoneList"
            :key="item.id"
            :span="6"
          >
            <el-card >
              <div class="item_card">
                <img :src="item.imgShow1">
              <span>{{item.goodsName}}</span>
              </div>
              
              
            </el-card>
          </el-col>
        </el-row>
      </div>
    </div>
  </div>
   
</template>

<script setup>
import router from "@/router";
import { onMounted, ref } from "vue"
import {selectGoodsPhone,selectGoodsCom} from "../../request/addGoods"
const phoneList=ref([]);
const comList=ref([]);
onMounted(async()=>{
  await selectGoodsPhone().then((res)=>{
    phoneList.value=res.data.data;
    console.log(phoneList.value);
  }).catch()
  await selectGoodsCom().then((res)=>{
    comList.value=res.data.data;
  }).catch()
})

const showDetail = (item) => {
  sessionStorage.setItem("goodsId", item);
  router.push({
    name: "goodDetail",
  });
};
</script>

<style lang="less" scoped>
.spIndex{
  width: 100%;
  display: flex;
  background-color: #f5f5f5;
  height: 100%;
  justify-content: center;
  align-items: center;
  flex-direction: column; 
  .top{
    width: 70%;
    min-height: 400px;
    min-width: 700px;
    margin-top: 50px;
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: #fff;
    .top_carousel{
      width: 80%;
      margin-top: 20px;
      .el-image {
            display: block;
            width: 100%;
            height: 100%;
            object-fit: cover; /* 确保图片能够适应其容器 */
        }
    }
  }
  .middle{
    display: flex;
    flex-direction: column;
    margin-top: 20px;
    background-color: #fff;
    width: 70%;
    .middleItem{
      .item_top{
        display: flex;
        flex-direction: row;
        align-items: center;
        background: linear-gradient(to right, #f16909, #67c23a);
        justify-content: space-between;
        height: 50px;
        h3{
          margin-left: 20px;
        }
        span{
          margin-right: 20px;
        }
      }
      img{
        width: 200px;
        height: 250px;
      }
      .item_card{
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
      }
    }
  }
  .priceStyle{
    font-size: 24px; /* 或者你想要的其他大小 */
    font-weight: bold; /* 可选：如果你还想让价格文本加粗 */
    color: #fd423e;
  }
  }  
</style>