<template>
  <div id="body">
    <header>
      <img src="./assets/schoolImg/bg-footer.png" class="background" />
      <img src="./assets/schoolImg/schoolLogo.png" class="schoolLogo" />
      <div class="title">
        工&nbsp;作&nbsp;量&nbsp;管&nbsp;理&nbsp;平&nbsp;台
      </div>
    </header>
    <div class="center">
      <div class="background1"></div>
      <div class="background2"></div>

      <div class="login">
        <span>登&nbsp;&nbsp;录</span><br />
        <label for="account">账号&nbsp;</label>
        <input type="text" id="account" v-model="username" required />
        <br />
        <label for="password">密码&nbsp;</label>
        <input type="password" id="password" v-model="password" required />
        <br />
        <input type="submit" value="确 定  " @click="login" />
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      username: "",
      password: "",
    };
  },
  methods: {
    login() {
      const self = this;
      if (this.$data.username == "" || this.$data.password == "") {
        alert("账号或密码不能为空！");
      } else {
        //向后端提交数据
        //需要url
        this.$axios
          .post(`${this.$domainName}/users/login`, {
            username: this.$data.username,
            password: this.$data.password,
          })
          .then((response) => {
            //成功时返回名字和身份
            if (response.data.response.code == 200) {
              localStorage.clear();
              localStorage.setItem("userName", response.data.data.username);
              localStorage.setItem("userIdentify", response.data.data.identify);
              if (response.data.data.identify == 0) {
                self.$router.push("/teacherhome");
              } else {
                self.$router.push("/managerhome");
              }
            } else {
              alert("登录失败！");
            }
          })
          .catch(function (error) {
            console.log(error);
          });
      }
    },
  },
};
</script>

<style scoped>
#body {
  width: 100vw;
  height: 100vh;
  box-sizing: border-box;
  overflow: hidden;
  background-color: rgb(251, 251, 251);
  font-family: "SimSun";
}

header img.schoolLogo {
  position: absolute;
  top: 5px;
  left: 20px;
  width: 200px;
}
header .title {
  position: absolute;
  color: white;
  font-size: 32px;
  font-weight: 600;
  top: 2vh;
}
div {
  margin-left: 50%;
  transform: translateX(-50%);
}
.center {
  margin-top: 17vh;
}

.background1 {
  position: absolute;
  z-index: -2;
  top: 220px;
  left: 130px;
  width: 300px;
  height: 300px;
  border-radius: 50%;
  background: linear-gradient(
    150deg,
    rgb(41, 58, 43, 0.6),
    rgb(55, 84, 43, 0.6),
    rgb(83, 132, 91, 0.6)
  );
}

.background2 {
  position: absolute;
  top: -70px;
  left: -180px;
  z-index: -2;
  width: 400px;
  height: 400px;
  border-radius: 50%;
  background: linear-gradient(
    -30deg,
    rgb(130, 178, 151),
    rgb(175, 200, 162),
    rgb(223, 235, 199)
  );
}

.login {
  position: absolute;
  padding-top: 5%;
  width: 350px;
  height: 420px;
  border-radius: 20px;
  backdrop-filter: blur(10px);
  background-color: rgba(128, 128, 128, 0.178);
  text-align: center;
  box-shadow: 0 5px 10px rgba(0, 0, 0, 0.247);
  text-shadow: 1px 2px 5px rgba(0, 0, 0, 0.096);
}

.login span {
  font-size: 250%;
  font-weight: 500;
  color: rgba(51, 52, 51, 0.9);
}

.login label {
  font-size: 120%;
  font-weight: 400;
  color: rgba(51, 52, 51, 0.9);
}

.login input {
  margin-top: 15%;
  margin-bottom: 5%;
  border-radius: 30px;
  padding-left: 10px;
  width: 65%;
  height: 35px;
  border: 0;
  outline: 0;
  font-size: 120%;
  color: rgba(51, 52, 51, 0.9);
}

.login input[type="submit"] {
  margin-top: 8%;
  width: 70px;
  height: 70px;
  border-radius: 50px;
  border: 3px solid rgba(255, 255, 255, 0.356);
  outline: 0;
  background-color: rgba(255, 255, 255, 0);
  font-size: 120%;
  font-weight: 500;
  color: whitesmoke;
  cursor: pointer;
}

.login input[type="submit"]:hover {
  background-color: rgba(83, 81, 81, 0.486);
  color: white;
}
@media screen and (max-width: 800px) {
  header .title {
    display: none;
  }
}
</style>