import { createStore } from 'vuex'

export default createStore({
  state: {
    id:null,
    isLogin:false,
    token:"",
    goodsList:[],
    nickName:""
  },
  getters: {
  },
  mutations: {
    updateIsLogin(state){
      state.isLogin=!state.isLogin;
    },
    updateId(state,id){
      state.id=id;
    },
    setGoodsList(state,data){
      state.goodsList=data
    },
    setNickName(state, nickName) {
      state.nickName = nickName;
    },
  },
  actions: {
  },
  modules: {
  }
})
