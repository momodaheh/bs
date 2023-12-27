<template>
  <div class="payOrderView">
    <div class="payOrderBox">
      <el-card class="order">
        <template #header>
          <el-page-header @back="goBack" title=" ">
              <template #content>
                <h2>待支付订单</h2>
              </template>
            </el-page-header>
        </template>
        <el-table :data="orderList" >
          <el-table-column label="商品信息" width="350px" header-align="center">
            <template #default="scope">
              <div class="itemStyle">
                <el-image :src="scope.row.imgShow1" />
                <span>{{ scope.row.goodsName }}</span>
                <span>{{ scope.row.attribute }}</span>
              </div>
            </template>
          </el-table-column>
          <el-table-column label="收货地址" width="300px" header-align="center" :show-overflow-tooltip="true">
            <template #default="scope">
              <div class="itemStyle2">
                <span>{{scope.row.contact}}</span>
                <span>{{ scope.row.telephone }}</span>
                <span>{{ scope.row.address }}</span>
              </div>
            </template>
          </el-table-column>
          <el-table-column label="数量" prop="number" width="100px" />
          <el-table-column label="价格" prop="price" width="100px" />
          <el-table-column label="操作" width="150px">
            <template #default="scope">
              <div class="itemStyle">
                <el-button type="primary" @click="toPay(scope.row.id)">支付</el-button>
              <el-button type="danger" @click="deleteClick(scope.row.id)">取消</el-button>
              </div>
              
            </template>
          </el-table-column>
        </el-table>
      </el-card>
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref } from "vue";
import { selectNopay ,deleteOrder} from "../../request/order";
import { ElMessage } from "element-plus";
import router from "@/router";
const userId = ref(null);
const orderList = ref([]);
onMounted(async () => {
  userId.value = sessionStorage.getItem("userId");
  await selectNopay(userId.value).then((res) => {
    orderList.value = res.data.data;
    console.log(orderList.value);
  });
});
const deleteClick=async(data)=>{
  await deleteOrder(data);
  await selectNopay(userId.value).then((res) => {
    orderList.value = res.data.data;
    console.log(orderList.value);
  });
  ElMessage.success("取消订单成功")
}
const toPay=async(data)=>{
  window.open('http://localhost:8082/alipay/pay?orderId='+data)
  
}
const goBack=()=>{
  router.go(-1);
}
</script>

<style lang="less" scoped>
.payOrderView {
  width: 100%;
  display: flex;
  background-color: #e7e3e2;
  height: 100%;
  min-height: 100vh;
  flex-direction: column;
  align-items: center;
  .payOrderBox {
    width: 70%;
    height: 100%;
    margin-top: 20px;
    .order{
      
        .itemStyle{
        display: flex;
        flex-direction: row;
        justify-content: space-around;
        align-items: center;
      }
      .itemStyle2{
        display: flex;
        flex-direction: column;
      }
      .el-image {
            display: block;
            width: 70px;
            height: 90px;
            object-fit: cover; /* 确保图片能够适应其容器 */
        }
      
    }
  }
}
</style>