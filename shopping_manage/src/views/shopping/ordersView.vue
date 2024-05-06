<template>
  <div class="payOrderView">
    <div class="payOrderBox">
      <el-card class="order">
        <template #header>
          <el-page-header @back="goBack" title=" ">
              <template #content>
                <h2>我的订单</h2>
              </template>
            </el-page-header>
        </template>
        <el-tabs v-model="activeTab" type="card">
          <el-tab-pane label="所有订单" name="all">
            <!-- 在这里展示所有订单的内容 -->
            <el-table :data="orderList">
              <el-table-column
                label="商品信息"
                width="250px"
                header-align="center"
              >
                <template #default="scope" >
                  <div class="itemStyle" @click="showDetail(scope.row.goodsId)">
                    <el-image :src="scope.row.imgShow1" />
                    <span>{{ scope.row.goodsName }}</span>
                    <span>{{ scope.row.attribute }}</span>
                  </div>
                </template>
              </el-table-column>
              <el-table-column
                label="收货地址"
                width="150px"
                header-align="center"
                :show-overflow-tooltip="true"
              >
                <template #default="scope">
                  <div class="itemStyle2">
                    <span>{{ scope.row.contact }}</span>
                    <span>{{ scope.row.telephone }}</span>
                    <span>{{ scope.row.address }}</span>
                  </div>
                </template>
              </el-table-column>
              <el-table-column label="数量" prop="number" width="100px" />
              <el-table-column label="价格" prop="price" width="100px" />
              <el-table-column label="支付单号" prop="payId" width="100px" />
              <el-table-column label="运单" prop="logisticsId" width="100px" />
              <el-table-column label="状态" prop="state" width="100px">
                <template #default="scope">
                  <span v-if="scope.row.state === 0">未支付</span>
                  <span v-if="scope.row.state === 1">未发货</span>
                  <span v-if="scope.row.state === 2">已发货</span>
                  <span v-if="scope.row.state === 3">已完成</span>
                </template>
              </el-table-column>
              <el-table-column label="操作" width="100px">
                <template #default="scope">
                  <el-button v-if="scope.row.state === 0" @click="toPay(scope.row.id)" >支付</el-button>
                  <!-- <el-button v-if="scope.row.state===1">未发货</el-button> -->
                  <el-button v-if="scope.row.state === 2" @click="showModel(scope.row)" type="success">确认收货</el-button>
                  <el-button
                    v-if="scope.row.state === 3"
                    type="primary"
                    @click="openDrawer(scope.row)"
                    >去评论</el-button
                  >
                </template>
              </el-table-column>
            </el-table>
          </el-tab-pane>
          <el-tab-pane label="未发货" name="unshipped">
            <!-- 在这里展示未发货订单的内容 -->
            <el-table :data="orderList1">
              <el-table-column
                label="商品信息"
                width="250px"
                header-align="center"
              >
                <template #default="scope">
                  <div class="itemStyle" @click="showDetail(scope.row.goodsId)">
                    <el-image :src="scope.row.imgShow1" />
                    <span>{{ scope.row.goodsName }}</span>
                    <span>{{ scope.row.attribute }}</span>
                  </div>
                </template>
              </el-table-column>
              <el-table-column
                label="收货地址"
                width="150px"
                header-align="center"
                :show-overflow-tooltip="true"
              >
                <template #default="scope">
                  <div class="itemStyle2">
                    <span>{{ scope.row.contact }}</span>
                    <span>{{ scope.row.telephone }}</span>
                    <span>{{ scope.row.address }}</span>
                  </div>
                </template>
              </el-table-column>
              <el-table-column label="数量" prop="number" width="100px" />
              <el-table-column label="价格" prop="price" width="100px" />
              <el-table-column label="支付单号" prop="payId" width="100px" />
              <el-table-column label="运单" prop="logisticsId" width="100px" />
              <el-table-column label="状态" prop="state" width="100px">
                <span>未发货</span>
              </el-table-column>
              <el-table-column label="操作" width="100px">
                <template >
                  <div class="itemStyle">
                    
                  </div>
                </template>
              </el-table-column>
            </el-table>
          </el-tab-pane>
          <el-tab-pane label="已发货" name="shipped">
            <el-table :data="orderList3">
              <el-table-column
                label="商品信息"
                width="250px"
                header-align="center"
              >
                <template #default="scope">
                  <div class="itemStyle" @click="showDetail(scope.row.goodsId)">
                    <el-image :src="scope.row.imgShow1" />
                    <span>{{ scope.row.goodsName }}</span>
                    <span>{{ scope.row.attribute }}</span>
                  </div>
                </template>
              </el-table-column>
              <el-table-column
                label="收货地址"
                width="150px"
                header-align="center"
                :show-overflow-tooltip="true"
              >
                <template #default="scope">
                  <div class="itemStyle2">
                    <span>{{ scope.row.contact }}</span>
                    <span>{{ scope.row.telephone }}</span>
                    <span>{{ scope.row.address }}</span>
                  </div>
                </template>
              </el-table-column>
              <el-table-column label="数量" prop="number" width="100px" />
              <el-table-column label="价格" prop="price" width="100px" />
              <el-table-column label="支付单号" prop="payId" width="100px" />
              <el-table-column label="运单" prop="logisticsId" width="100px" />
              <el-table-column label="状态" prop="state" width="100px">
                <span>已发货</span>
              </el-table-column>
              <el-table-column label="操作" width="100px">
                <template #default="scope">
                  <div class="itemStyle">
                    <el-button @click="showModel(scope.row)" type="success"
                      >确认收货</el-button
                    >
                  </div>
                </template>
              </el-table-column>
            </el-table>
          </el-tab-pane>
          <el-tab-pane label="已完成" name="completed">
            <!-- 在这里展示已完成订单的内容 -->
            <el-table :data="orderList2">
              <el-table-column
                label="商品信息"
                width="250px"
                header-align="center"
              >
                <template #default="scope">
                  <div class="itemStyle" @click="showDetail(scope.row.goodsId)">
                    <el-image :src="scope.row.imgShow1" />
                    <span>{{ scope.row.goodsName }}</span>
                    <span>{{ scope.row.attribute }}</span>
                  </div>
                </template>
              </el-table-column>
              <el-table-column
                label="收货地址"
                width="150px"
                header-align="center"
                :show-overflow-tooltip="true"
              >
                <template #default="scope">
                  <div class="itemStyle2">
                    <span>{{ scope.row.contact }}</span>
                    <span>{{ scope.row.telephone }}</span>
                    <span>{{ scope.row.address }}</span>
                  </div>
                </template>
              </el-table-column>
              <el-table-column label="数量" prop="number" width="100px" />
              <el-table-column label="价格" prop="price" width="100px" />
              <el-table-column label="支付单号" prop="payId" width="100px" />
              <el-table-column label="运单" prop="logisticsId" width="100px" />
              <el-table-column label="状态" prop="state" width="100px">
                <span>已完成</span>
              </el-table-column>
              <el-table-column label="操作" width="100px">
                <template #default="scope">
                  <div class="itemStyle">
                    <el-button
                    v-if="scope.row.state === 3"
                    type="primary"
                    @click="openDrawer(scope.row)"
                    >去评论</el-button
                  >
                  </div>
                </template>
              </el-table-column>
            </el-table>
          </el-tab-pane>
        </el-tabs>
      </el-card>
    </div>
  </div>
  <el-drawer v-model="drawer" size="50%" title="发表评论" :before-close="drawerClose">
    <div class="commentBox">
      <div class="top">
        <div>
          <el-image :src="currentRow.imgShow1" />
        </div>
        <div class="right">
          <h2>{{ currentRow.goodsName }}</h2>
          <p>
            支付:<span class="priceStyle">{{ currentRow.price }}元</span>
          </p>
          <p class="attributeStyle">{{ currentRow.attribute }}</p>
          <p>数量:{{ currentRow.number }}</p>
        </div>
      </div>
      <el-radio-group v-model="commentDate.commentType">
        <el-radio label=0>好评</el-radio>
        <el-radio label=1>差评</el-radio>
      </el-radio-group>
      <el-input
        v-model="commentDate.content"
        :rows="5"
        type="textarea"
        placeholder="请输入评论"
      />
      <el-button type="primary" @click="pushPrimary" round>发表评论</el-button>
    </div>
  </el-drawer>

  <!-- 确认删除品牌弹窗 -->
    <el-dialog v-model="dialogshou" title="确认收货" width="400px">
      <span>请确认是否收到货</span>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogshou = false">关闭</el-button>
          <el-button type="primary" @click="shou">
            确认
          </el-button>
        </span>
      </template>
    </el-dialog>
</template>

<script setup>
import router from "@/router";
import { onMounted, reactive, ref } from "vue";
import { selectNoReceipt, selectAll ,addComment,Shouhuo,selectFinsh,noFa} from "../../request/order";
import { ElMessage } from "element-plus";
const userId = ref(null);
const orderList = ref([]);
const orderList1 = ref([]);
const orderList2 = ref([]);
const orderList3 = ref([]);
const drawer = ref(false);
const currentRow = ref(null);
const dialogshou =ref(false);
const activeTab = ref("all"); // 默认显示所有订单的标签页
const commentDate =reactive({
  userId:null,
  goodId:null,
  commentType:null,
  content:""
})
onMounted(async () => {
  userId.value = sessionStorage.getItem("userId");
  await selectAll(userId.value).then((res) => {
    orderList.value = res.data.data;
  });
  await noFa(userId.value).then((res) => {
    orderList1.value = res.data.data;
  });
  await selectNoReceipt(userId.value).then((res) => {
    orderList3.value = res.data.data;
  });
  await selectFinsh(userId.value).then((res) => {
    orderList2.value = res.data.data;
  });
});

const openDrawer = (data) => {
  drawer.value = true;
  currentRow.value = data;
  console.log(currentRow.value);
};

const drawerClose = () => {
  commentDate.comment = "";
  drawer.value=false
};

const goBack=()=>{
  router.go(-1);
}
const toPay=async(data)=>{
  window.open('http://localhost:8082/alipay/pay?orderId='+data)
  
}
const pushPrimary=async()=>{
  commentDate.userId=userId.value;
  commentDate.goodId=currentRow.value.goodId;
  if(commentDate.commentType===null){
    ElMessage.error("请选择评论类型")
  }else{
    await addComment(commentDate);
    drawer.value=false;
    commentDate.commentType=null;
    commentDate.content="";
  }
  
}
const showModel=(data)=>{
  dialogshou.value=true;
  currentRow.value = data;
}
const shou =async()=>{
  await Shouhuo(currentRow.value.id);
  dialogshou.value=false;
  ElMessage.success("确认收货成功");
  await selectAll(userId.value).then((res) => {
    orderList.value = res.data.data;
  });
  await noFa(userId.value).then((res) => {
    orderList1.value = res.data.data;
  });
  await selectNoReceipt(userId.value).then((res) => {
    orderList3.value = res.data.data;
  });
  await selectFinsh(userId.value).then((res) => {
    orderList2.value = res.data.data;
  });
}

const showDetail = (item) => {
  sessionStorage.setItem("goodsId", item);
  router.push({
    name: "goodDetail",
  });
};
</script>

<style lang="less">
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
    .order {
      .itemStyle {
        display: flex;
        flex-direction: row;
        justify-content: space-around;
        align-items: center;
      }
      .itemStyle2 {
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
.commentBox {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-around;
  height: 100%;
  .top {
    display: flex;
    flex-direction: row;
    width: 100%;
    .right {
      margin-left: 50px;
      display: flex;
      flex-direction: column;
      justify-content: space-around;
      .priceStyle {
        font-size: 24px; /* 或者你想要的其他大小 */
        font-weight: bold; /* 可选：如果你还想让价格文本加粗 */
        color: #fd423e;
      }
      .attributeStyle {
        display: flex;
        justify-content: center;
        padding: 5px 10px;
        border: 1px solid #d2c7c7;
        cursor: pointer;
      }
    }

    .el-image {
      width: 200px; /* 设置图片宽度为卡片宽度 */
      height: 250px; /* 设置图片高度为你想要的值，这里假设为180px */
    }
  }
}
</style>