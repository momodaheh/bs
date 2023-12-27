<template>
<div class="box">
  <div id="salesChart" style="width: 100%; height: 350px"></div>
  <div class="bottom">
    <div id="categoryChart" style="width: 50%; height: 300px"></div>
    <div>
      <el-card style="height: 300px">
        <h4>近30天销售额Top3</h4>
        <el-table :data="goods">
          <el-table-column label="商品信息" width="300px" header-align="center">
            <template #default="scope">
              <div class="itemStyle">
                <el-image :src="scope.row.imgShow1" />
                <span>{{scope.row.goodsName}}</span>
              </div>
            </template>
          </el-table-column>
          <el-table-column label="成交额" prop="totalPrice" >
            <template #default="scope">
              <span>{{scope.row.totalPrice}}元</span>
            </template>
          </el-table-column>
        </el-table>
      </el-card>
    </div>
  </div>
</div>
  
   
</template>

<script setup>
import * as echarts from "echarts";
import { onMounted, ref } from "vue";
import { selectDailyPrice ,selectPriceByCategory,selectGoodsByPriceTop} from "../request/order";

const salesChart = ref(null);
const dailyPrice = ref([]);
const categoryPrice=ref([]);
const goods=ref([]);
onMounted(async () => {
  await selectDailyPrice()
    .then((res) => {
      dailyPrice.value = res.data.data;
    })
    .catch();

  await selectPriceByCategory()
    .then((res) => {
      categoryPrice.value = res.data.data;
    })
    .catch();

    await selectGoodsByPriceTop()
    .then((res) => {
      goods.value = res.data.data;
    })
    .catch();
  initCharts();
});
function initCharts() {
  initSalesChart();
  initCategoryChart();
}
function initSalesChart() {
  salesChart.value = echarts.init(document.getElementById("salesChart"));

  const option = {
    title: { // 添加标题配置
      text: '最近30天每日销售额', // 设置标题文本
      left: 'center', // 设置标题位置，居中显示
      top: 20 // 设置标题距离顶部的距离
    },
    xAxis: {
      type: "category",
      data: dailyPrice.value.map((item) => item.date),
    },
    yAxis: {
      type: "value",
    },
    series: [
      {
        data: dailyPrice.value.map((item) => item.dailyPrice),
        type: "line",
      },
    ],
    tooltip: {
      
    }
  };

  salesChart.value.setOption(option);
}

function initCategoryChart() {
  const categoryChart = echarts.init(document.getElementById("categoryChart"));

  const option = {
    title: { // 添加标题配置
      text: '不同分类的物品销售额', // 设置标题文本
      left: 'center', // 设置标题位置，居中显示
      top: 20 // 设置标题距离顶部的距离
    },
    tooltip: {
      trigger: 'item', // 触发方式为数据项触发
      formatter: '{a} <br/>{b} : {c} ({d}%)' // 提示框内容格式化
    },
    series: [
      {
        name: '销售额',
        type: 'pie',
        radius: ['50%', '70%'], // 环形饼图半径
        avoidLabelOverlap: false, // 防止标签重叠
        label: {
          show: true,
          position: 'inside', // 标签位置在内部
          align: 'center',
          verticalAlign: 'middle',
          fontSize: 14,
          fontWeight: 'bold',
          formatter: '{b}: {@value} ({d}%)', // 标签内容格式化
          color: '#333'
        },
        emphasis: {
          label: {
            show: true,
            fontSize: '16',
            fontWeight: 'bold'
          }
        },
        labelLine: {
          show: false // 隐藏标签线
        },
        data: categoryPrice.value.map((item) => ({
          value: item.totalPrice,
          name: item.category_name
        }))
      }
    ]
  };

  categoryChart.setOption(option);
}
</script>

<style lang="less" scoped>
.box{
  display: flex;
  flex-direction: column;
  .bottom{
    display: flex;
    flex-direction: row;
    
    .itemStyle{
        display: flex;
        flex-direction: row;
        justify-content: space-around;
        align-items: center;
      }
      .el-image {
            display: block;
            width: 40px;
            height: 60px;
            object-fit: cover; /* 确保图片能够适应其容器 */
        }
  }
}
</style>