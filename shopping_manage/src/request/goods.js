import request from ".";

//查询所有品牌
export function selectBrands(){
    return request({
        method:"post",
        url:'/brand/selectBrands',
    })
}

//添加品牌
export function addBrand(data){
    return request({
        method:"post",
        url:'/brand/addBrand',
        headers: {
            'Content-Type': 'text/plain'
          },
        data:data
    })
}

//删除品牌
export function deleteBrand(data){
    return request({
        method:"post",
        url:'/brand/deleteBrand',
        headers: {
            'Content-Type': 'text/plain'
          },
        data:data
    })
}

//根据brandId查询分类
export function selectCategoryByBrandId(data){
    return request({
        method:"post",
        url:'/category/selectCategoryByBrandId',
        headers: {
            'Content-Type': 'text/plain'
          },
        data:data
    })
}

//删除某品牌的某个分类
export function deletebrand_Category(brandId,categoryId){
    return request({
        method:"post",
        url:'/category/deletebrand_Category',
        data: {
            brandId: brandId,
            categoryId: categoryId,
        }
    })
}

//查询所有分类
export function selectCategorys(){
    return request({
        method:"post",
        url:'/category/selectCategorys',
    })
}

//添加品牌下的分类
export function addbradn_Category(brandId,categoryId){
    return request({
        method:"post",
        url:'/category/addbradn_Category',
        data: {
            brandId: brandId,
            categoryId: categoryId,
        }
    })
}

//添加分类
export function addcategory(data){
    return request({
        method:"post",
        url:'/category/addcategory',
        headers: {
            'Content-Type': 'text/plain'
          },
        data:data
    })
}

//删除分类
export function deletecategory(data){
    return request({
        method:"post",
        url:'/category/deletecategory',
        headers: {
            'Content-Type': 'text/plain'
          },
        data:data
    })
}

//查询某个分类下的属性
export function selectAttributeByCategoryId(data){
    return request({
        method:"post",
        url:'/attribute/selectAttributeByCategoryId',
        headers: {
            'Content-Type': 'text/plain'
          },
        data:data
    })
}

//添加分类下的属性
export function addAttribute(categoryId,attributeName){
    return request({
        method:"post",
        url:'/attribute/addAttribute',
        data: {
            categoryId: categoryId,
            attributeName:attributeName
        }
    })
}

//删除分类下的属性
export function deleteAttribute(data){
    return request({
        method:"post",
        url:'/attribute/deleteAttribute',
        headers: {
            'Content-Type': 'text/plain'
          },
        data:data
    })
}

//查询某个属性的属性值
export function selectAttributeValueById(data){
    return request({
        method:"post",
        url:'/attributeValue/selectAttributeValueById',
        headers: {
            'Content-Type': 'text/plain'
          },
        data:data
    })
}

//添加某个属性的属性值
export function addAttributeValue(attribute_id,attributeValue){
    return request({
        method:"post",
        url:'/attributeValue/addAttributeValue',
          data: {
            attribute_id: attribute_id,
            attributeValue:attributeValue
        }
    })
}

//删除某个属性的属性值
export function deleteAttributeValue(data){
    return request({
        method:"post",
        url:'/attributeValue/deleteAttributeValue',
        headers: {
            'Content-Type': 'text/plain'
          },
        data:data
    })
}