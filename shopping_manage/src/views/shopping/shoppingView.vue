<template>
  <div class="all">
    <div class="header">
      <span>国货电子商店</span>
      <div class="searchBox">
        <el-input v-model="keyWord" placeholder="请输入搜索关键词" />
        <el-button style="width: 50px ; margin-left: 20px" @click="search" type="primary">搜索</el-button>
      </div>
      <el-dropdown>
        <span class="el-dropdown-link">
          {{nickName}}
        </span>
        <el-icon class="el-icon--right">
        <arrow-down />
      </el-icon>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item
              ><router-link to="/" class="linkStyle"
                >首页</router-link
              ></el-dropdown-item
            >
            <el-dropdown-item
              ><router-link to="/shoppingCar" class="linkStyle"
                >购物车</router-link
              ></el-dropdown-item
            >
            <el-dropdown-item
              ><router-link to="/selfCenter" class="linkStyle"
                >个人中心</router-link
              ></el-dropdown-item
            >
            <el-dropdown-item class="linkStyle" v-if="!isLogin" @click="toLogin"
              >登录</el-dropdown-item
            >
            <el-dropdown-item class="linkStyle" v-if="isLogin" @click="logout"
              >退出</el-dropdown-item
            >
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
    <div class="box">
      <router-view></router-view>
    </div>
  </div>
</template>

<script setup>
import { computed, onMounted, ref } from "vue";
import { useStore } from "vuex";
import { ElMessage } from "element-plus";
import router from "@/router";
import { selectGoodsByName } from "../../request/addGoods";
import {selectUserInfor} from "../../request/user"
const store = useStore();
const keyWord = ref("");
const nickName = computed(() => {
  return store.state.nickName || 'NickName';
});
const userIdInSession = ref(sessionStorage.getItem("userId"));
const isLogin = ref(userIdInSession.value !== null);
onMounted( async() => {
  isLogin.value = userIdInSession.value !== null;
  if(userIdInSession.value!=null){
     let NickName=  (await selectUserInfor(userIdInSession.value)).data.data.nickName
    store.commit('setNickName', NickName);
  }
  
});
let toLogin = () => {
  router.push("/consumerLogin");
};

const search = async () => {
  const keyword = keyWord.value.trim();

  if (!keyword) {
    ElMessage.error("请填写想要搜索的内容");
    return;
  }

  try {
    const response = await selectGoodsByName(keyword);

    if (response.data.code === 0) {
      store.commit("setGoodsList", response.data.data);
      console.log(response.data.data);
    } else {
      ElMessage.error("搜索失败，请稍后重试");
    }
  } catch (error) {
    ElMessage.error("搜索过程中发生错误，请稍后重试");
  }

  router.push({
    name: "goodShopping",
  });
};
let logout = () => {
  sessionStorage.removeItem('userId');
  userIdInSession.value = null;
  store.commit('setNickName', null);
  isLogin.value = false;
  router.push('/');
};
</script>

<style lang="less" >
.all {
  width: 100%;
  min-height: 100vh;
  display: flex;
  flex-direction: column;
}
.header {
  width: 100%;
  min-height: 50px;
  background-color: #333333;
  display: flex;
  align-items: center;
  justify-content: space-between;
  color: #f2f2f2;
  .searchBox {
    display: flex;
    flex-direction: row;
  }
  span {
    margin-left: 100px;
    margin-right: 100px;
  }
  .el-dropdown-link {
    color: #f2f2f2;
    display: flex;
    align-items: center;
    .linkStyle {
      text-decoration: none;
      color: #fff;
    }
  }
}
.el-dropdown-menu__item {
  display: flex;
  justify-content: center;
  width: 100px;
  font-size: 18px;
  .linkStyle {
    text-decoration: none;
    color: #000;
  }
}
.el-icon--right {
  color: #f2f2f2;
}
.box {
  width: 100%;
  height: 100%;
}
</style>