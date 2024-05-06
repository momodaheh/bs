<template>
  <div class="listView">
    <div class="listBox">
      <el-page-header @back="goBack"  title="Back" />
        <el-row :gutter="20" v-if="goodsList.length > 0" >
          <el-col 
            v-for="item in goodsList"
            :key="item.id"
            :span="6"
            style="margin-top:20px"
          >
            <el-card @click="showDetail(item)" >
              <div class="item_card">
                <img :src="item.imgShow1">
              <span>{{item.goodsName}}</span>
              <span class="priceStyle">￥{{item.minprice}}</span>
              </div>
              
              
            </el-card>
          </el-col>
        </el-row>
        <el-empty v-else :image-size="200" description="没有查询到您想要的物品"/>
    </div>
    
  </div>
</template>

<script setup>
import router from "@/router";
import { computed } from "vue";
import { useStore } from "vuex";

const store=useStore();
const goodsList = computed(() => store.state.goodsList);
const showDetail=(item)=>{
    sessionStorage.setItem("goodsId", item.id);
     router.push({
        name:'goodDetail',
      })
}
const goBack=()=>{
  router.go(-1);
}
</script>

<style lang="less" scoped>
.listView {
  width: 100%;
  display: flex;
  background-color: #e7e3e2;
  height: 100%;
  min-height: 100vh;
  flex-direction: column;
  align-items: center;
}

.listBox {
  height: 100%;
  min-height: 100vh;
  background-color: #f5f5f5;
  width: 80%;
}

.item_card {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  img {
    width: 200px; /* 设置图片宽度为卡片宽度 */
    height: 250px; /* 设置图片高度为你想要的值，这里假设为180px */
    
  }
  .priceStyle{
    font-size: 24px; /* 或者你想要的其他大小 */
    font-weight: bold; /* 可选：如果你还想让价格文本加粗 */
    color: #fd423e;
  }
}
</style>