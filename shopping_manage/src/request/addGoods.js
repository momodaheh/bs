import request from ".";
// 上传图片
export function upload(file) {
  const formData = new FormData();
  formData.append("file", file);
  return request({
    method: "post",
    url: "/files/upload",
    headers: {
      "Content-Type": "multipart/form-data"
    },
    data: formData
  });
}

//删除上传的图片
export function flag(data){
    return request({
        method:"post",
        url:'/files/flag',
        headers: {
            'Content-Type': 'text/plain'
          },
        data:data
    })
}

//添加收获物品
export function addGoods(data){
  return request({
      method:"post",
      url:'/goods/addGoods',
      data:data
  })
}

//获取图片列表
export function selectGoods(){
  return request({
      method:"post",
      url:'/goods/selectGoods',
  })
}

//删除物品
export function deleteGood(data){
  return request({
      method:"post",
      url:'/goods/deleteGood',
      headers: {
          'Content-Type': 'text/plain'
        },
      data:data
  })
}

//搜索库存
export function selectGoodsInfoById(data){
  return request({
      method:"post",
      url:'/goodsInfo/selectGoodsInfoById',
      headers: {
          'Content-Type': 'text/plain'
        },
      data:data
  })
}

//搜索库存
export function deleteGoodsInfo(data){
  return request({
      method:"post",
      url:'/goodsInfo/deleteGoodsInfo',
      headers: {
          'Content-Type': 'text/plain'
        },
      data:data
  })
}

export function addGoodsInfo(data){
  return request({
      method:"post",
      url:'/goodsInfo/addGoodsInfo',
      data:data
  })
}

export function updateGoodsInfo(data){
  return request({
      method:"post",
      url:'/goodsInfo/updateGoodsInfo',
      data:data
  })
}

//获取手机物品列表
export function selectGoodsPhone(){
  return request({
      method:"post",
      url:'/goods/selectGoodsPhone',
  })
}

//获取电脑物品列表
export function selectGoodsCom(){
  return request({
      method:"post",
      url:'/goods/selectGoodsCom',
  })
}

//获取电脑物品列表
export function selectGoodsWatch(){
  return request({
      method:"post",
      url:'/goods/selectGoodsWatch',
  })
}

//搜索物品
export function selectGoodsByName(data){
  return request({
      method:"post",
      url:'/goods/selectGoodsByName',
      headers: {
          'Content-Type': 'text/plain'
        },
      data:data
  })
}

//获取单个物品的信息
export function selectGoodsById(data){
  return request({
      method:"post",
      url:'/goods/selectGoodsById',
      headers: {
          'Content-Type': 'text/plain'
        },
      data:data
  })
}