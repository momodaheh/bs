<template>
  <div class="all">
    <div class="login_box">
      <div>
        <h3>登录</h3>
      </div>
      <div class="item">
        <el-input
          type="text" 
          placeholder="请输入账号" v-model="admin.userName" />
      </div>
      <div class="item">
        <el-input
          type="password" 
          placeholder="请输入密码" v-model="admin.password" show-password/>
      </div>
      <div class="item">
        <el-button @click="login" round>登录</el-button>
      </div>
    </div>
  </div>
</template>

<script>
import {reactive} from "vue";
import {adminLogin} from "../request/user"
import router from '@/router';
export default {
  setup(){
    let admin =reactive({
      userName:"",
      password:""
    });
    let login =()=>{
      if(!admin.userName||!admin.password){
        alert("用户名或者密码不能为空")
        return;
      }
      adminLogin(admin).then(res=>{
        if(res.data.code===0){
          router.push("/indexView")
        }else{
          admin.userName="";
          admin.password="";
          alert("用户名密码错误")
        }
      })
    }
    return{
      admin,
      login
    }
  }
}
</script>

<style lang="less" scoped>
.all{
  width: 100%;
  height: 100%;
  background-image: url("../assets/img/login_bg.jpg");
  background-repeat: no-repeat;
  background-position: center;
  background-size: cover;
  position: absolute;
  display: flex;
  align-items: center;
  justify-content: center;
}
.login_box{
  background-color: rgba(255, 255, 255, 0.5);
  width: 400px;
  height: 300px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  h3{
    margin: 10px;
  }
  .el-input{
    margin: 10px;
  }
  .el-button{
    margin:10px;
  }
}
</style>