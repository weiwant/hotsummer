<template>
  <div id="body">
    <header>
      <img src="../assets/schoolImg/bg-footer.png" class="background" />
      <img src="../assets/schoolImg/schoolLogo.png" class="schoolLogo" />
      <div class="title">
        工&nbsp;作&nbsp;量&nbsp;管&nbsp;理&nbsp;平&nbsp;台
      </div>
    </header>
    <div class="center">
      <div class="background1"></div>
      <div class="background2"></div>
      <div class="form">
        <div class="form-title">登&nbsp;&nbsp;&nbsp;录</div>
        <div class="form-item">
          <label>账号</label>
          <input type="text" v-model="username" />
        </div>
        <div class="form-item">
          <label>密码</label>
          <input type="text" v-model="password" />
        </div>
        <div class="form-submit"><span>确</span><span>认</span></div>
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
      if (this.$data.username === "" || this.$data.password === "") {
        alert("账号或密码不能为空！");
      } else {
        this.$axios
          .post(`/users/login`, {
            username: this.$data.username,
            password: this.$data.password,
          })
          .then((response) => {
            //成功时返回名字和身份
            if (response.data.response.code == 200) {
              alert("登陆成功！");
              localStorage.clear();
              localStorage.setItem("userName", response.data.data.username);
              localStorage.setItem("userIdentify", response.data.data.identify);
              localStorage.setItem("token", response.data.data.token);
              if (response.data.data.identify == 0) {
                self.$router.push("/TeacherHome");
              } else {
                self.$router.push("/ManagerHome");
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
}

header img.schoolLogo {
  position: absolute;
  top: 5px;
  left: 20px;
  width: 110px;
}
header .title {
  position: absolute;
  margin-left: 50%;
  transform: translateX(-50%);
  color: white;
  font-size: 32px;
  font-weight: 600;
  top: 2vh;
}
.center {
  position: relative;
  margin-left: 50%;
  transform: translateX(-50%);
  margin-top: 17vh;
}
.background1 {
  position: absolute;
  z-index: -2;
  top: 220px;
  left: 230px;
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
  left: -100px;
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

.form {
  margin-left: 50%;
  transform: translateX(-50%);
  padding: 10%;
  padding-top: 5%;
  width: 350px;
  height: 420px;
  border-radius: 20px;
  backdrop-filter: blur(10px);
  background-color: rgba(128, 128, 128, 0.178);
  text-align: center;
  box-shadow: 0 5px 10px rgba(0, 0, 0, 0.247);
}
.form-title {
  margin-bottom: 60px;
  font-size: 30px;
  font-weight: 500;
  color: rgb(47, 60, 50);
}

.form-item {
  width: 100%;
  margin-bottom: 20px;
  color: rgb(47, 60, 50);
}
.form-item label {
  display: block;
  text-align: left;
  font-size: 13px;
  font-weight: 500;
}
.form-item input {
  padding-left: 10px;
  width: 100%;
  height: 38px;
  border: 0;
  border-radius: 10px;
  font-size: 18px;
}
.form-submit {
  display: flex;
  justify-content: space-evenly;
  padding: 5px 0;
  margin-top: 40px;
  background-color: rgb(44, 64, 44);
  border-radius: 10px;
  color: whitesmoke;
  font-size: 20px;
  font-weight: 500;
  cursor: pointer;
}
.form-submit:hover {
  background-color: rgb(34, 46, 34);
}
@media screen and (max-width: 800px) {
  header .title {
    display: none;
  }
}
</style>
