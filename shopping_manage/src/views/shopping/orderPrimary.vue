<template>
  <div class="orderPrimaryView">
    <div class="orderPrimaryBox">
      <el-card class="orderPrimaryCard">
        <template #header>
          <el-page-header @back="goBack" title=" ">
              <template #content>
                <h2>订单</h2>
              </template>
            </el-page-header>
        </template>
        <el-table :data="order">
          <el-table-column
            label="商品信息"
            min-width="400px"
            header-align="center"
          >
            <template #default="scope">
              <div
                class="itemStyle"
                v-if="scope.row.orderInfo && scope.row.orderInfo.imgShow1"
              >
                <el-image :src="scope.row.orderInfo.imgShow1" />
                <span>{{ scope.row.orderInfo.goodsName }}</span>
                <span>{{ scope.row.orderInfo.attribute }}</span>
              </div>
            </template>
          </el-table-column>
          <el-table-column label="单价" prop="price" width="150px" >
            <template #default="scope">
              <span>{{scope.row.price}}元</span>
            </template>
          </el-table-column>
          <el-table-column label="数量" width="150px">
            <template #default="scope">
              <el-input-number
                style="width: 100px"
                v-model="scope.row.amount"
                @input="updateTotalPrice(scope.$index)"
              />
            </template>
          </el-table-column>
          <el-table-column label="总价" prop="totalPrice" width="150px" >
            <template #default="scope">
              <span>{{scope.row.totalPrice}}元</span>
            </template>
          </el-table-column>
        </el-table>
        <div class="middle">
          <h3>我的收货地址:</h3>
          <el-row class="addressBox">
            <el-col
              v-for="(address, index) in addressList"
              :key="address.id"
              :span="6"
              :offset="index % 3 != 0 ? 3 : 0"
            >
              <el-card
                :class="{ selectedAddress: address.id === selectedAddressId }"
                @click="selectAddress(address)"
              >
                <div class="addressItem">
                  <div>
                    <p>{{ address.contact }}</p>
                    <p>{{ address.telephone }}</p>
                    <p>{{ address.address }}</p>
                  </div>
                  <div class="iconGroup">
                    <svg
                      class="icon"
                      aria-hidden="true"
                      @click="updateAddressClick(address)"
                    >
                      <use xlink:href="#icon-xiugai"></use>
                    </svg>
                    <svg
                      class="icon"
                      aria-hidden="true"
                      @click="deleteAddressClick(address)"
                    >
                      <use xlink:href="#icon-shanchu1"></use>
                    </svg>
                  </div>
                </div>
              </el-card>
            </el-col>
            <div class="addressAddItem" @click="dialogAddAddress = true">
              <svg class="icon" aria-hidden="true">
                <use xlink:href="#icon-jiahao1"></use>
              </svg>
            </div>
          </el-row>
        </div>
        <el-divider />
        <div class="buttonStyle">
          <el-button type="primary" @click="submitOrders">确认提交</el-button>
        </div>
      </el-card>
    </div>
    <!-- 修改地址弹窗 -->
    <el-dialog v-model="dialogUpdateAddress" title="修改地址" width="400px">
      <span>收件人:</span>
      <el-input v-model="selectAdd.contact" autocomplete="off" />
      <span>联系电话:</span>
      <el-input v-model="selectAdd.telephone" autocomplete="off" />
      <span>收货地址:</span>
      <el-input v-model="selectAdd.address" autocomplete="off" />
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogUpdateAddress = false">关闭</el-button>
          <el-button type="primary" @click="updateAddressPrimary">
            确认
          </el-button>
        </span>
      </template>
    </el-dialog>

    <!-- 确认删除品牌弹窗 -->
    <el-dialog v-model="dialogDeleteAddress" title="删除地址" width="400px">
      <span>请确认是否删除</span>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogDeleteAddress = false">关闭</el-button>
          <el-button type="primary" @click="deleteAddressPrimary">
            确认
          </el-button>
        </span>
      </template>
    </el-dialog>

    <!-- 添加地址弹窗 -->
    <el-dialog v-model="dialogAddAddress" title="添加地址" width="400px">
      <span>收件人:</span>
      <el-input v-model="newAddress.contact" autocomplete="off" />
      <span>联系电话:</span>
      <el-input v-model="newAddress.telephone" autocomplete="off" />
      <span>收货地址:</span>
      <el-input v-model="newAddress.address" autocomplete="off" />
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogAddAddress = false">关闭</el-button>
          <el-button type="primary" @click="addAddressPrimary">
            确认
          </el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { onMounted, reactive, ref } from "vue";
import {
  selectUserAddress,
  updateAddress,
  deleteAddress,
  addAddress,
} from "../../request/user";
import { selectOrderInfo, addOrder } from "../../request/order";
import router from "@/router";
import { ElMessage } from "element-plus";
const userId = ref(sessionStorage.getItem("userId"));
const addressList = ref([]);
const dialogUpdateAddress = ref(false);
const dialogDeleteAddress = ref(false);
const dialogAddAddress = ref(false);
const selectAdd = ref({});
const selectedAddressId = ref(null);
const order = ref(JSON.parse(sessionStorage.getItem("order")));
const newAddress = reactive({
  userId: userId,
  contact: "",
  telephone: "",
  address: "",
});
onMounted(async () => {
  const response1 = await selectUserAddress(userId.value);
  if (response1.data.code === 0) {
    addressList.value = response1.data.data;
  }

  if (Array.isArray(order.value)) {
    for (let i = 0; i < order.value.length; i++) {
      const response2 = await selectOrderInfo(order.value[i]);
      if (response2.data.code === 0) {
        order.value[i].orderInfo = response2.data.data; // 将获取到的订单信息添加到原对象中
        order.value[i].totalPrice =
          order.value[i].price * order.value[i].amount;
      }
    }
  }
  
});
const updateAddressClick = (data) => {
  dialogUpdateAddress.value = true;
  selectAdd.value = { ...data };
};
const updateAddressPrimary = async () => {
  await updateAddress(selectAdd.value);
  dialogUpdateAddress.value = false;
  const response1 = await selectUserAddress(userId.value);
  if (response1.data.code === 0) {
    addressList.value = response1.data.data;
  }
};

const deleteAddressClick = (data) => {
  dialogDeleteAddress.value = true;
  selectAdd.value = { ...data };
};

const deleteAddressPrimary = async () => {
  await deleteAddress(selectAdd.value.id);
  dialogDeleteAddress.value = false;
  const response1 = await selectUserAddress(userId.value);
  if (response1.data.code === 0) {
    addressList.value = response1.data.data;
  }
};

const addAddressPrimary = async () => {
  await addAddress(newAddress);
  dialogAddAddress.value = false;
  const response1 = await selectUserAddress(userId.value);
  if (response1.data.code === 0) {
    addressList.value = response1.data.data;
  }
};

const updateTotalPrice = (index) => {
  order.value[index].totalPrice =
    order.value[index].price * order.value[index].amount;
};

const selectAddress = (address) => {
  selectedAddressId.value = address.id;

  // 将地址ID插入到order中
  order.value.forEach((item) => {
    item.addressId = address.id;
  });
  console.log(order.value);
};

const submitOrders = async () => {
  let isStockError = false;
  if (selectedAddressId.value === null) {
    ElMessage.error("请选择地址");
  } else {
    for (let i = 0; i < order.value.length; i++) {
      if(order.value[i].amount>order.value[i].orderInfo.number){
        ElMessage.error('没有这么多库存');
        isStockError = true;
      }
    }
    for (let i = 0; i < order.value.length&&!isStockError; i++) {
        await addOrder(order.value[i]);
    }
    if (!isStockError) {
      router.push({
        name: "payOrder",
      });
    }
  }
};

const goBack=()=>{
  router.go(-1);
}
</script>

<style lang="less" scoped>
.orderPrimaryView {
  width: 100%;
  display: flex;
  background-color: #e7e3e2;
  height: 100%;
  min-height: 100vh;
  flex-direction: column;
  align-items: center;
  .orderPrimaryBox {
    width: 70%;
    height: 100%;
    margin-top: 20px;
    .orderPrimaryCard {
      .itemStyle {
        display: flex;
        flex-direction: row;
        justify-content: space-around;
        align-items: center;
      }
      .el-image {
        display: block;
        width: 70px;
        height: 90px;
        object-fit: cover; /* 确保图片能够适应其容器 */
      }
      .buttonStyle {
        display: flex;
        justify-content: flex-end;
        margin-top: 20px;
      }
    }
    .middle {
      width: 700px;
      margin-top: 15px;
      .addressBox {
        margin-top: 15px;
        margin-bottom: 15px;
        .addressItem {
          display: flex;
          flex-direction: column;
          justify-content: space-between;
          height: 140px;
          .iconGroup {
            display: flex;
            flex-direction: row;
            justify-content: space-between;
            align-items: center;
            margin-top: 15px;
            .icon {
              width: 25px;
              height: 25px;
            }
          }
        }
      }
      .addressAddItem {
        height: 180px;
        border-radius: 20%;
        border: 2px dashed #c3bfbf;
        margin-left: 10px;
        display: flex;
        justify-content: center;
        align-items: center;
        .icon {
          width: 35px;
          height: 35px;
        }
      }
      .selectedAddress {
        /* 添加您的选中样式，例如背景色、边框等 */
        background-color: #f0f0f0;
      }
    }
  }
}
</style>