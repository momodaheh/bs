<template>
  <div class="all">
    <div class="login_box" v-show="show">
      <div>
        <h3>登录</h3>
      </div>
      <div class="item">
        <el-input
          type="text"
          placeholder="请输入账号"
          v-model="consumer.userName"
        />
      </div>
      <div class="item">
        <el-input
          type="password"
          placeholder="请输入密码"
          v-model="consumer.password"
          show-password
        />
      </div>
      <div class="item">
        <el-button @click="login" type="primary" v-show="show" round
          >登录</el-button
        >
        <el-button @click="showRegister" type="success" round>注册</el-button>
      </div>
    </div>
    <div class="login_box" v-show="!show">
      <div>
        <h3>注册</h3>
      </div>
      <div class="item">
        <span>账号:</span>
        <el-input
          type="text"
          placeholder="请输入账号"
          v-model="consumer.userName"
        />
      </div>
      <div class="item">
        <span>密码:</span>
        <el-input
          type="password"
          placeholder="请输入密码"
          v-model="consumer.password"
          show-password
        />
      </div>
      <div class="item">
        <span>确认密码:</span>
        <el-input
          type="password"
          placeholder="请确认密码"
          v-model="checkPassword"
          show-password
        />
      </div>
      <div class="item">
        <el-button @click="RegisterUser" type="success" round>注册</el-button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { reactive, ref } from "vue";
import { conLogin, selectRegisterUserName, Register } from "../../request/user";
import router from "@/router";
import { useStore } from "vuex";
import { ElMessage } from "element-plus";
import { useRoute } from "vue-router";

const route = useRoute();
const store = useStore();
let consumer = reactive({
  userName: "",
  password: "",
});
const checkPassword = ref("");
let show = ref(true);
let login = () => {
  if (!consumer.userName || !consumer.password) {
    ElMessage.error("用户名或者密码不能为空");
    return;
  }
  conLogin(consumer).then((res) => {
    if (res.data.code === 0) {
      console.log(res.data.data);
      sessionStorage.setItem("userId", res.data.data.id);
      store.commit("updateIsLogin");
      // 获取用户原本尝试访问的页面路径
        const redirectRoute = route.query.redirect || "/";

        // 重定向到用户原本尝试访问的页面
        router.push(redirectRoute);
    } else {
      consumer.userName = "";
      consumer.password = "";
      ElMessage.error("用户名密码错误");
    }
  });
};
const showRegister = () => {
  show.value = !show.value;
};

const RegisterUser = async () => {
  const res = await selectRegisterUserName(consumer.userName);
  const user = res.data.data;
  if (user) {
    ElMessage.error("该用户名已被注册");
  } else {
    if (checkPassword.value === consumer.password) {
      await Register(consumer);
    } else {
      ElMessage.error("两次密码不一致");
    }
  }
  show.value = !show.value;
};
</script>

<style lang="less" scoped>
.all {
  width: 100%;
  height: 100%;
  background-image: url("../../assets/img/login_bg.jpg");
  background-repeat: no-repeat;
  background-position: center;
  background-size: cover;
  position: absolute;
  display: flex;
  align-items: center;
  justify-content: center;
}
.login_box {
  background-color: rgba(255, 255, 255, 0.5);
  width: 400px;
  height: 300px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  h3 {
    margin: 10px;
  }
  .el-input {
    margin: 10px;
  }
  .el-button {
    margin: 10px;
  }
}
.item {
  display: flex;
  flex-direction: row;
  justify-items: center;
  align-items: center;
  span {
    width: 100px;
    display: flex;
    justify-content: flex-end;
  }
}
</style>