import { createRouter, createWebHistory } from 'vue-router'


const routes = [
  {
    path:'/adminLogin',
    name:'loginView',
    component:()=>import('../views/loginView.vue')
  },
  {
    path:'/consumerLogin',
    name:'consumerLogin',
    component:()=>import('../views/shopping/consumerLogin.vue')
  },
  {
    path:'/',
    name:'shoppingView',
    redirect:'/shoppingIndex',
    component:()=>import('../views/shopping/shoppingView.vue'),
    children:[
      {
        path:'/shoppingIndex',
        name:'shoppingIndex',
        component:()=>import('../views/shopping/shoppingIndex.vue'),
        
      },
      {
        path:'/selfCenter',
        name:'selfCenter',
        component:()=>import('../views/shopping/selfCenter.vue'),
        
      },
      {
        path:'/shoppingCar',
        name:'shoppingCar',
        component:()=>import('../views/shopping/shoppingCar.vue'),
        
      },
      {
        path:'/goodShopping',
        name:'goodShopping',
        component:()=>import('../views/shopping/goodShopping.vue'),
        
      },
      {
        path:'/goodDetail',
        name:'goodDetail',
        component:()=>import('../views/shopping/goodDetail.vue'),
        
      },
      {
        path:'/payOrder',
        name:'payOrder',
        component:()=>import('../views/shopping/payOrder.vue'),
        
      },
      {
        path:'/orderPrimary',
        name:'orderPrimary',
        component:()=>import('../views/shopping/orderPrimary.vue'),
        
      },
      {
        path:'/ordersView',
        name:'ordersView',
        component:()=>import('../views/shopping/ordersView.vue'),
        
      },
      
    ]
  },
  {
    path:'/indexView',
    name:'indexView',
    component:()=>import('../views/indexView.vue'),
    children:[
      {
        path:'/homeView',
        name:'homeView',
        component:()=>import('../views/homeView.vue'),
        
      },
      {
        path:'/userView',
        name:'userView',
        component:()=>import('../views/userView.vue'),
        
      },
      {
        path:'/commentView',
        name:'commentView',
        component:()=>import('../views/commentView.vue'),
        
      },
      {
        path:'/goodsList',
        name:'goodsList',
        component:()=>import('../views/goods/goodsList.vue')
        
      },
      {
        path:'/goodsBrand',
        name:'goodsBrand',
        component:()=>import('../views/goods/goodsBrand.vue')
        
      },
      {
        path:'/addGood',
        name:'addGood',
        component:()=>import('../views/goods/addGood.vue')
      },
      {
        path:'/goodsCategory',
        name:'goodsCategory',
        component:()=>import('../views/goods/goodsCategory.vue')
      },
      {
        path:'/orderList',
        name:'orderList',
        component:()=>import('../views/orders/orderList.vue')
      },
      {
        path:'/orderReturn',
        name:'orderReturn',
        component:()=>import('../views/orders/orderReturn.vue')
      },
      {
        path:'/orderDelivery',
        name:'orderDelivery',
        component:()=>import('../views/orders/orderDelivery.vue')
      },
    ]
  },
  
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

router.beforeEach((to, from, next) => {
  const userId = sessionStorage.getItem('userId');
  const shoppingViewRoutes = [
    '/shoppingIndex',
    '/selfCenter',
    '/shoppingCar',
    '/goodShopping',
    '/goodDetail',
    '/payOrder',
    '/orderPrimary',
    '/ordersView'
  ];

  if (
    to.path === '/consumerLogin' ||
    to.path === '/' ||
    shoppingViewRoutes.includes(to.path)
  ) {
    if (to.path === '/consumerLogin' || to.path === '/shoppingIndex' || userId) {
      next();
    } else if (!userId && to.path !== '/shoppingIndex') {
      // 添加查询参数来保存原本尝试访问的页面路径
      next({ path: '/consumerLogin', query: { redirect: to.fullPath } });
    } else {
      next();
    }
  } else {
    next();
  }
});

export default router
