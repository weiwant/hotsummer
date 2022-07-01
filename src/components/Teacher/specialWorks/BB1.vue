<template>
  <div class="componentSubsection category">
    <div class="title">课程建设BB1</div>
    <form>
      <tr>
        <td>级别:    </td>
        <td>
          
          <input type="radio" name="class" id="jibie" checked="checked"> 国家级        
          <input type="radio" name="class" id="jibie" > 省级  
          <input type="radio" name="class" id="jibie" > 校级
        </td>
        <br>
      </tr>
      <tr>
        <td>项目名称:   </td>
        <td>
          <input type="text" placeholder="请输入所建设项目的名称" id="name">
        </td>
        <br>
      </tr>
      <tr>
        <td>课程类别:   </td>
        <td>
          <select>
            <option value="leibie" selected="selected">课程建设项目</option>
            <option value="leibie">企业-教育部课程建设项目</option>
            <option value="leibie">国家级课程建设项目</option>
            <option value="leibie">线上国家级一流本科课程建设项目</option>
            <option value="leibie">线下国家级一流本科课程建设项目</option>
            <option value="leibie">线上线下混合式国家级一流本科课程建设项目</option>
            <option value="leibie">社会实践国家级一流本科课程建设项目</option>
            <option value="leibie">“武大通识3.0”</option>
            <option value="leibie">“慕课”课程</option>
            <option value="leibie">其他</option>
          </select>
          
        </td>
        <br>
      </tr>
      <tr>
        <td>进展情况:   </td>
        <td>
          <input type="radio" name="class" id="jibie" checked="checked">立项         
          <input type="radio" name="class" id="jibie" >结题  
          <input type="radio" name="class" id="jibie" >建设中
        </td>
        <br>
      </tr>
      <tr>
        <td>上传附件：   </td>
        <td>
          <input type="file">
        </td>
        <br>
      </tr>
      <tr>
        <input type="submit" value="提交">
        <input type="reset"  value="重置">
      </tr>
    </form>
  
  </div>
</template>

<script>
export default {};
</script>


<style scoped>
</style>
