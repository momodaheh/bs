<template>
  <div class="addGoods">
    
    <div class="addBox">
      <el-form>
        <el-form-item label="名称:" :span="12" class="formItem">
          <el-input v-model="good.goodsName"></el-input>
        </el-form-item>
        <el-form-item label="品牌:"  class="formItem">
          <el-select v-model="good.brandId" >
            <el-option 
              v-for="item in brandList"
              :key="item.id"
              :label="item.brand"
              :value="item.id"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="分类:" class="formItem">
          <el-select v-model="good.categoryId">
            <el-option 
              v-for="item in categoryList"
              :key="item.id"
              :label="item.category_name"
              :value="item.id"
            />
          </el-select>
        </el-form-item> 
        <el-form-item label="展示图片(最多3张):" class="formItem">
          </el-form-item>
    </el-form>
      <el-upload
              v-model="fileList"
              :http-request="customRequest"
              :on-success="handleSuccess"
              :before-upload="beforeUpload"
              list-type="picture"
              :on-remove="deleteFile"
            >
              <el-button size="small" type="primary" :style="width='100%'">点击上传</el-button>
       </el-upload>
       <div class="buttonBox">
        <el-button type="primary" @click="addGoodsClick" >确认</el-button>
       </div>
       
    </div>
  </div>
</template>

<script>
import { onMounted, reactive, ref } from 'vue';
import {addGoods, upload} from '../../request/addGoods'
import {selectBrands,selectCategorys} from '../../request/goods'
import { ElMessage } from "element-plus";
export default {
  setup() {
    // 已上传的文件列表
    const fileList = ref([]);
    const brandList=ref([]);
    const categoryList=ref([]);
    const good=reactive({
      goodsName:'',
      brandId:null,
      categoryId:null,
      imgShow1:'',
      imgShow2:'',
      imgShow3:'',
      imgDetail:''
    })
    onMounted(async()=>{
      const response = await selectBrands();
      if (response.data.code === 0) {
        brandList.value = response.data.data;
      }
      const response1=await selectCategorys();
      if (response1.data.code === 0) {
        categoryList.value = response1.data.data;
        
      }
    })
    // 成功上传后的回调函数
    function handleSuccess() {
      console.log('上传成功');
    }
    // 文件上传前的钩子函数
    function beforeUpload(file) {
      // 检查文件类型是否为图片
      const isJpgOrPng = file.type === 'image/jpeg' || file.type === 'image/png';
      if (!isJpgOrPng) {
        ElMessage.error('只能上传 JPG 或 PNG 格式的图片!');
      }
      // 检查图片大小是否小于 500kb
      const isLt5M = file.size / 1024 / 1024 < 0.5;
      if (!isLt5M) {
        ElMessage.error('上传图片必须小于 500KB!');
      }
      return isJpgOrPng && isLt5M;
    }

    // 自定义请求方法
    async function customRequest(options) {
      // 发送 POST 请求将文件内容传递给后端服务器
      
      try {
        // 调用后端 API 进行文件上传
        const response = await upload( options.file);
        // 将返回的文件路径添加到已上传文件列表中

        fileList.value.push({
          name: options.file.name,
          url: response.data.data
        });
        // 触发 on-success 回调函数
        options.onSuccess();
      } catch (error) {
        // 处理错误
        console.error(error);
        this.$message.error('上传失败，请重试');
      }
    }

    // 删除文件的方法
    async function deleteFile(file) {
        // console.log(file);
        // await flag(file.name);
        fileList.value = fileList.value.filter((item) => item.name !== file.name);
        // console.log(fileList.value);
    }
    
    const addGoodsClick=async()=>{
      if (fileList.value && fileList.value.length >= 1) {
        good.imgShow1 = fileList.value[0]?.url;
      }
      if (fileList.value && fileList.value.length >= 2) {
        good.imgShow2 = fileList.value[1]?.url;
      }
      if (fileList.value && fileList.value.length >= 3) {
        good.imgShow3 = fileList.value[2]?.url;
      }
      await addGoods(good);
      location.reload()
      // fileList.value.length=0;
      // good.goodsName="";
      // good.categoryId=null;
      // good.brandId=null;
      // good.imgShow1="";
      // good.imgShow2="";
      // good.imgShow3="";
      // good.imgDetail="";
    }
    return {
      fileList,
      handleSuccess,
      beforeUpload,
      customRequest,
      deleteFile,
      good,
      brandList,
      categoryList,
      addGoodsClick
    };
  },
};
</script>

<style lang="less" scoped>
  .addGoods{
    display: flex;
    height: 100%;
    align-items: center;
    justify-content: center;
    .addBox{
      width: 800px;
      height: 600px;
      background-color: #fff;
      padding:20px;
      .formItem{
        margin-top: 10px;
      }
      .buttonBox{
        width: 100%;
        display: flex;
        flex-direction: row;
        justify-content: center;
        align-items: center;
      }
    }
  }
</style>