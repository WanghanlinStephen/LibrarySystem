<template>
  <div>
    <div class="demo-image__placeholder">
      <div class="block">
        <el-image :src="ruleForm.url">
          <div slot="placeholder" class="image-slot">
            加载中<span class="dot">...</span>
          </div>
        </el-image>
      </div>
    </div>
    <ul :model="ruleForm" class="demo-detail">
      <li >书名:{{ruleForm.name}}</li>
      <li >作者:{{ruleForm.author}}</li>
      <li >价格:{{ruleForm.price}}</li>
    </ul>
  </div>

</template>

<script>
  export default {
    data() {
      return {
        ruleForm: {
          id: '',
          name: '',
          author: '',
          price:'',
          url:''
        },
      }
    },
    created() {
      const _this = this
      axios.get('http://localhost:8181/book/findById/'+this.$route.query.id).then(function(resp){
        _this.ruleForm = resp.data
        console.log("This is the data");
        console.log(resp.data);
      })
    }
  }
</script>

<style scoped>
  .demo-image__placeholder {
    margin-top: 10%;
    width:50%;
  }

</style>


