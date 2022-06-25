<template>
<div id="body">
    <header>高 级 教 师 工 作 量 管 理 系 统</header>
    <div class="center">
        <div class="background1"></div>
        <div class="background2"></div>
        <div class="login">
            <span>登&nbsp;&nbsp;录</span><br>
            <label for="account">名字:</label>
            <input type="text" id="account" v-model="username" required>
            <br>
            <label for="password">密码:</label>
            <input type="password" id='password' v-model="password" required>
            <br>
            <input type="submit" value="确 定  " @click="login">
        </div>
    </div>
</div>
</template>

<script>
import axios from 'axios'

export default{
    data(){
        return{
            username: '',
            password: ''
        }
    },

    methods:{

        login(){
            var _this = this;
            if(this.$data.username == '' || this.$data.password == ''){
                alert('账号或密码不能为空！')
            }else{
                //向后端提交数据
                //需要url
                axios.post('https://4e22-2001-250-4001-5012-9d4f-8594-5e18-94ed.ap.ngrok.io/users/login',{
                    username: this.$data.username,
                    password: this.$data.password
                })
                .then(function(response){
                    //成功时返回名字和身份
                    if(response.data.success){
                        console.log(response);
                        alert("登录成功！");
                        localStorage.clear();
                        if(response.data.data.identify == 1){
                            localStorage.setItem('managerName', response.data.data.username);
                            localStorage.setItem('userIdentify', response.data.data.identify);
                            this.$router.push('/managerhome')
                        }else if(response.data.data.identify == 2){
                            localStorage.setItem('teacherName', response.data.data.username);
                            localStorage.setItem('userIdentify', response.data.data.identify);
                            this.$router.push('/teacherhome');
                        }
                    }else{
                        alert("登录失败！");
                        console.log(response);
                    }
                })
                .catch(function(error){
                    console.log(error);
                })
            }
        }

    }

}

</script>

<style>
.body{
    box-sizing: border-box;
    overflow: hidden;
    background-color: rgb(255, 255, 255);
    font-family: 'SimSun';
}
header{
    position: relative;
    height: 0px;
    top: 20px;
    left: 0;
    transform: translateX(-50%);
    color: rgb(128, 128, 128);
    font-size: 50px;
    font-weight: 600;
    text-shadow: 3px 2px 2px rgba(23, 23, 41, 0.281);
    white-space: nowrap;
}
div{
    margin-left: 50%;
    transform: translateX(-50%);
}
.center{
    margin-top: 30vh;
}


.background1{
    position: absolute;
    z-index: -2;
    top: 220px;
    left: 130px;
    width: 300px;
    height: 300px;
    border-radius: 50%;
    background:linear-gradient(150deg,rgb(99, 156, 202),rgb(61, 102, 165),rgb(24, 77, 138)
    );
}


.background2{
    position: absolute;
    top: -70px;
    left: -180px;
    z-index: -2;
    width: 400px;
    height: 400px;
    border-radius: 50%;
    background: linear-gradient(-30deg,rgb(142, 118, 173),rgb(103, 168, 212),rgb(106, 187, 173));
}

.login{
    position: absolute;
    padding-top: 5%;
    width: 350px;
    height: 400px;
    border-radius: 20px;
    backdrop-filter: blur(10px);
    background-color: rgba(128, 128, 128, 0.178);
    text-align: center;
    box-shadow: 0 5px 10px rgba(0, 0, 0, 0.247);
    text-shadow: 1px 2px 5px rgba(0, 0, 0, 0.096);
}

.login span{
    font-size: 250%;
    font-weight: 500;
    color: rgba(255, 255, 255, 0.671);
   
}

.login label{
    font-size: 120%;
    font-weight: 400;
    color: rgba(255, 255, 255, 0.733);
}

.login input{
    margin-top: 15%;
    margin-bottom: 5%;
    border-radius: 30px;
    padding-left: 10px;
    width: 65%;
    height: 30px;
    border: 0;
    outline: 0;
    font-size: 120%;
    color: rgba(0, 0, 0, 0.685);
}

.login input[type="submit"]{
    margin-top: 10%;
    width: 70px;
    height: 70px;
    border-radius: 50px;
    border: 3px solid rgba(255, 255, 255, 0.356);
    outline: 0;
    background-color: rgba(255, 255, 255, 0);
    font-size: 120%;
    font-weight: 500;
    color: rgba(255, 255, 255, 0.856);
    cursor: pointer;
}

.login input[type="submit"]:hover{
    background-color: rgba(83, 81, 81, 0.486);
}
</style>