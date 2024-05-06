import request from ".";
//获取用户购物车信息
export function selectShoppingCarById(data){
    return request({
        method:"post",
        url:'/shoppingCar/selectShoppingCarById',
        headers: {
            'Content-Type': 'text/plain'
          },
        data:data
    })
  }

  //删除购物车记录
export function deleteShoppingCar(data){
  return request({
      method:"post",
      url:'/shoppingCar/deleteShoppingCar',
      headers: {
          'Content-Type': 'text/plain'
        },
      data:data
  })
}

//添加购物车记录
export function addShoppingCar(data){
  return request({
      method:"post",
      url:'/shoppingCar/addShoppingCar',
      data:data
  })
}

 //查询用户未支付订单
 export function selectNopay(data){
  return request({
      method:"post",
      url:'/order/selectNopay',
      headers: {
          'Content-Type': 'text/plain'
        },
      data:data
  })
}

//删除订单
export function deleteOrder(data){
  return request({
      method:"post",
      url:'/order/deleteOrder',
      headers: {
          'Content-Type': 'text/plain'
        },
      data:data
  })
}

//确认订单时获取详情
export function selectOrderInfo(data){
  return request({
      method:"post",
      url:'/order/selectOrderInfo',
      data:data
  })
}

//确认订单
export function addOrder(data){
  return request({
      method:"post",
      url:'/order/addOrder',
      data:data
  })
}

//支付
export function pay(data){
  return request({
      method:"get",
      url:'/alipay/pay',
      headers: {
        'Content-Type': 'text/plain'
      },
      params: { orderId: data }
  })
}

//管理员查询订单
export function selectOrders(){
  return request({
      method:"post",
      url:'/order/selectOrders',
  })
}

//查询未发货订单
export function selectOrdersStateOne(){
  return request({
      method:"post",
      url:'/order/selectOrdersStateOne',
  })
}
//发货
export function fahuo(id,logisticsId){
  return request({
      method:"post",
      url:'/order/fahuo',
        data: {
          id: id,
          logisticsId:logisticsId
      }
  })
}

//查询未发货
export function selectNoReceipt(data){
  return request({
      method:"post",
      url:'/order/selectNoReceipt',
      headers: {
        'Content-Type': 'text/plain'
      },
      data:data
  })
}

//查询未发货
export function selectAll(data){
  return request({
      method:"post",
      url:'/order/selectAll',
      headers: {
        'Content-Type': 'text/plain'
      },
      data:data
  })
}

//获取每天的收银
export function selectDailyPrice(){
  return request({
      method:"post",
      url:'/order/selectDailyPrice',
  })
}

//获取当前月不同分类的收银
export function selectPriceByCategory(){
  return request({
      method:"post",
      url:'/order/selectPriceByCategory',
  })
}

//获取当前月收银前3物品
export function selectGoodsByPriceTop(){
  return request({
      method:"post",
      url:'/order/selectGoodsByPriceTop',
  })
}

//查询未发货
export function addComment(data){
  return request({
      method:"post",
      url:'/comment/addComment',
      data:data
  })
}

export function Shouhuo(data){
  return request({
      method:"post",
      url:'/order/Shouhuo',
      headers: {
        'Content-Type': 'text/plain'
      },
      data:data
  })
}

export function selectFinsh(data){
  return request({
      method:"post",
      url:'/order/selectFinsh',
      headers: {
        'Content-Type': 'text/plain'
      },
      data:data
  })
}

export function noFa(data){
  return request({
      method:"post",
      url:'/order/noFa',
      headers: {
        'Content-Type': 'text/plain'
      },
      data:data
  })
}

export function selectOrdresById(data){
  return request({
      method:"post",
      url:'/order/selectOrdresById',
      headers: {
        'Content-Type': 'text/plain'
      },
      data:data
  })
}

export function selectOrdresStateOneById(data){
  return request({
      method:"post",
      url:'/order/selectOrdresStateOneById',
      headers: {
        'Content-Type': 'text/plain'
      },
      data:data
  })
}