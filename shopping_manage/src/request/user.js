import request from ".";

//管理员登录
export function adminLogin(data){
    return request({
        method:"post",
        url:'/user/adminLogin',
        data:data
    })
}

//用户登录
export function conLogin(data){
    return request({
        method:"post",
        url:'/user/conLogin',
        data:data
    })
}

//获取用户信息
export function selectUserInfor(data){
    return request({
        method:"post",
        url:'/userInfor/selectUserInfor',
        headers: {
            'Content-Type': 'text/plain'
          },
        data:data
    })
}

//获取用户的收货地址
export function selectUserAddress(data){
    return request({
        method:"post",
        url:'/address/selectUserAddress',
        headers: {
            'Content-Type': 'text/plain'
          },
        data:data
    })
}

//修改收获地址
export function updateAddress(data){
    return request({
        method:"post",
        url:'/address/updateAddress',
        data:data
    })
}

//删除用户的收货地址
export function deleteAddress(data){
    return request({
        method:"post",
        url:'/address/deleteAddress',
        headers: {
            'Content-Type': 'text/plain'
          },
        data:data
    })
}

//添加收获地址
export function addAddress(data){
    return request({
        method:"post",
        url:'/address/addAddress',
        data:data
    })
}

//修改个人信息地址
export function updateUserInfor(data){
    return request({
        method:"post",
        url:'/userInfor/updateUserInfor',
        data:data
    })
}

//用户列表查询
export function selectUsers(){
    return request({
        method:"post",
        url:'/user/selectUsers',
    })
}

export function deleteUser(data){
    return request({
        method:"post",
        url:'/user/deleteUser',
        headers: {
            'Content-Type': 'text/plain'
          },
        data:data
    })
}

export function resetPassword(data){
    return request({
        method:"post",
        url:'/user/resetPassword',
        headers: {
            'Content-Type': 'text/plain'
          },
        data:data
    })
}

export function selectUsersByuserName(data){
    return request({
        method:"post",
        url:'/user/selectUsersByuserName',
        headers: {
            'Content-Type': 'text/plain'
          },
        data:data
    })
}

export function selectComments(){
    return request({
        method:"post",
        url:'/comment/selectComments',
        headers: {
            'Content-Type': 'text/plain'
          },
    })
}

export function deleteComment(data){
    return request({
        method:"post",
        url:'/comment/deleteComment',
        headers: {
            'Content-Type': 'text/plain'
          },
        data:data
    })
}

export function selectCommentsBygoodId(data){
    return request({
        method:"post",
        url:'/comment/selectCommentsBygoodId',
        headers: {
            'Content-Type': 'text/plain'
          },
        data:data
    })
}

export function selectCommentByContent(data){
    return request({
        method:"post",
        url:'/comment/selectCommentByContent',
        headers: {
            'Content-Type': 'text/plain'
          },
        data:data
    })
}
export function selectRegisterUserName(data){
    return request({
        method:"post",
        url:'/user/selectRegisterUserName',
        headers: {
            'Content-Type': 'text/plain'
          },
        data:data
    })
}

export function Register(data){
    return request({
        method:"post",
        url:'/user/Register',
        data:data
    })
}


export function selectAUser(data){
    return request({
        method:"post",
        url:'/user/selectAUser',
        headers: {
            'Content-Type': 'text/plain'
          },
        data:data
    })
}

//发货
export function ResetPassworSelf(id,password){
    return request({
        method:"post",
        url:'/user/ResetPassworSelf',
          data: {
            id: id,
            password:password
        }
    })
  }