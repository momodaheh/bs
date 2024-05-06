<template>
  <div class="selfView">
    <div class="content">
      <el-page-header @back="goBack"  class="pageHeader"/>
      <div class="top">
        <div>
          <h1>{{ user.info.nickName }}的个人中心</h1>
          <span>欢迎{{ user.info.nickName }}~~</span>
          <div class="updateInfoItem" @click="updateInfoClick">
            <span>修改个人信息></span>
          </div>
          <div class="updatePassItem" @click="dialogPassword=true">
            <span>修改密码></span>
          </div>
        </div>
        <div>
          <el-descriptions :column="1">
            <el-descriptions-item label="手机号:">{{
              user.info.telephone
            }}</el-descriptions-item>
            <el-descriptions-item label="邮箱地址:">{{
              user.info.email
            }}</el-descriptions-item>
            <el-descriptions-item label="性别:">
              <span v-if="user.info.sex === 1">男</span>
              <span v-else-if="user.info.sex === 0">女</span>
            </el-descriptions-item>
          </el-descriptions>
        </div>
      </div>
      <el-divider style="width: 700px" />
      <div class="middle">
        <h3>我的收货地址:</h3>
        <el-row class="addressBox">
          <el-col
            v-for="(address, index) in addressList"
            :key="address.id"
            :span="6"
            :offset="index % 3 != 0 ? 3 : 0"
          >
            <el-card>
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
      <el-divider style="width: 700px" />
      <div class="bottom">
        <div class="bottom_item" @click="toPayOrder">
          <svg class="icon" aria-hidden="true">
            <use xlink:href="#icon-guangdianhuikuane"></use>
          </svg>
          <div class="pStyle">
            <p>待支付订单</p>
            <p>点击查看</p>
          </div>
        </div>
        <div class="bottom_item" @click="toOrder">
          <svg class="icon" aria-hidden="true">
            <use xlink:href="#icon-dingdantongji"></use>
          </svg>
          <div class="pStyle">
            <p>我的订单</p>
            <p>点击查看</p>
          </div>
        </div>
      </div>
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

    <!-- 修改个人信息弹窗 -->
    <el-dialog v-model="dialogUpdateInfo" title="修改个人信息" width="400px">
      <span>昵称:</span>
      <el-input v-model="newInfo.nickName" autocomplete="off" />
      <span>联系电话:</span>
      <el-input v-model="newInfo.telephone" autocomplete="off" />
      <span>邮箱地址:</span>
      <el-input v-model="newInfo.email" autocomplete="off" />
      <span>性别:</span>
      <el-select v-model="newInfo.sex" style="margin-top: 3px" >
        <el-option label="男" value=1 />
        <el-option label="女" value=0 />
      </el-select>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogUpdateInfo = false">关闭</el-button>
          <el-button type="primary" @click="updateInfoPrimary()">
            确认
          </el-button>
        </span>
      </template>
    </el-dialog>

    <!-- 修改密码 -->
    <el-dialog v-model="dialogPassword" title="修改密码" width="400px">
      <span>原密码:</span>
      <el-input v-model="password" autocomplete="off" />
      <span>新密码:</span>
      <el-input v-model="newPassword" autocomplete="off" type="password" />
      <span>确认密码:</span>
      <el-input v-model="newPassword1" autocomplete="off" type="password" />
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogPassword = false">关闭</el-button>
          <el-button type="primary" @click="updatePassword">
            确认
          </el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import {
  selectUserInfor,
  selectUserAddress,
  updateAddress,
  deleteAddress,
  addAddress,
  updateUserInfor,
  selectAUser,
  ResetPassworSelf
} from "../../request/user";
import { useStore } from "vuex";
import { onMounted, reactive, ref } from "vue";
import router from "@/router";
import { ElMessage ,} from "element-plus";
const store = useStore();
const userId = sessionStorage.getItem("userId");
if (userId) {
  store.commit("updateId", userId);
}
const addressList = ref([]);
const user = reactive({ info: {} });
const selectAdd = ref({});
const password =ref("");
const newPassword=ref("");
const newPassword1=ref("");
const dialogUpdateAddress = ref(false);
const dialogDeleteAddress = ref(false);
const dialogAddAddress = ref(false);
const dialogUpdateInfo = ref(false);
const dialogPassword =ref(false);
const newAddress = reactive({
  userId: store.state.id,
  contact: "",
  telephone: "",
  address: "",
});
const newInfo = ref({});
onMounted(async () => {
  const response = await selectUserInfor(store.state.id);
  if (response.data.code === 0) {
    user.info = response.data.data;
  }
  const response1 = await selectUserAddress(store.state.id);
  if (response1.data.code === 0) {
    addressList.value = response1.data.data;
  }
});
const updateAddressClick = (data) => {
  dialogUpdateAddress.value = true;
  selectAdd.value = { ...data };
};
const updateAddressPrimary = async () => {
  await updateAddress(selectAdd.value);
  dialogUpdateAddress.value = false;
  const response1 = await selectUserAddress(store.state.id);
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
  const response1 = await selectUserAddress(store.state.id);
  if (response1.data.code === 0) {
    addressList.value = response1.data.data;
  }
};

const addAddressPrimary = async () => {
  await addAddress(newAddress);
  dialogAddAddress.value = false;
  const response1 = await selectUserAddress(store.state.id);
  if (response1.data.code === 0) {
    addressList.value = response1.data.data;
  }
};

const updateInfoClick = () => {
  dialogUpdateInfo.value = true;
  newInfo.value = { ...user.info };
  console.log(newInfo.value);
};

const updateInfoPrimary = async () => {
  await updateUserInfor(newInfo.value);
  dialogUpdateInfo.value = false;
  const response = await selectUserInfor(store.state.id);
  if (response.data.code === 0) {
    user.info = response.data.data;
  }
  store.commit('setNickName', user.info.nickName);
};

const toPayOrder = () => {
  router.push({
    name: "payOrder",
  });
};

const toOrder = () => {
  router.push({
    name: "ordersView",
  });
};

const updatePassword =async()=>{
  let res=await selectAUser(store.state.id);
  let user=res.data.data;
  if(user.password!=password.value){
    ElMessage.error("原密码错误");
    password.value="";
    newPassword.value="";
    newPassword1.value="";
  }else if(newPassword.value!=newPassword1.value){
    ElMessage.error("两次密码不一致")
  }else{
    await ResetPassworSelf(store.state.id,newPassword.value)
    ElMessage.success("修改成功")
    dialogPassword.value=false;
     password.value="";
    newPassword.value="";
    newPassword1.value="";
  }
}
const goBack=()=>{
  router.go(-1);
}
</script>

<style lang="less" scoped>
.selfView {
  width: 100%;
  display: flex;
  // background-color: #e7e3e2;
  background-color: #f5f5f5;
  height: 100vh;
  justify-content: center;
  align-items: center;
  overflow-y: auto;
  .el-page-header{
    width: 90%;
    height: 50px;
    display: flex;
    
  }
  .content {
    width: 65%;
    height: 100%;
    min-width: 700px;
    background-color: #fff;
    display: flex;
    flex-direction: column;
    align-items: center;
    .top {
      width: 700px;
      display: flex;
      flex-direction: row;
      justify-content: space-between;
      margin-top: 10px;
      // border-bottom: 1px solid #c3bfbf;
      .updateInfoItem {
        color: #ff6700;
      }
      .updatePassItem {
        color: #001eff;
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
      // border-bottom: 1px solid #c3bfbf;
    }
    .bottom {
      width: 700px;
      display: flex;
      flex-direction: row;
      margin-top: 15px;
      .bottom_item {
        width: 50%;
        display: flex;
        flex-direction: row;
        align-items: center;
        justify-content:center;
        .pStyle {
          margin-left: 30px;
          font-size: 18px;
          font-weight: 400;
          color: #757575;
        }
      }
    }
  }
  .icon {
    width: 100px;
    height: 100px;
  }
}
</style>