import { createStore } from 'vuex'

export default createStore({
  state: {
    id:null,
    isLogin:false,
    token:"",
    goodsList:[]
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
    }
  },
  actions: {
  },
  modules: {
  }
})
