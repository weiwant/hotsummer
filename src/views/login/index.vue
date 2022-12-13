<template>
  <div id="body">
    <header>
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
          <input type="text" v-model="username" placeholder="人事号" />
        </div>
        <div class="form-item">
          <label>密码</label>
          <input type="password" v-model="password" placeholder="身份证后6位" />
        </div>
        <div class="form-submit" @click="login">
          <span>确</span><span>认</span>
        </div>
      </div>
      <div class="loading" v-show="showLoading">
        <Loading />
      </div>


    </div>
  </div>
</template>

<script>
import { Message } from "element-ui";
import Loading from "@/components/Loading.vue";
export default {
  components: {
    Loading,
  },
  data() {
    return {
      username: "",
      password: "",
      valid_username: false,
      valid_password: false,
      showLoading: false,
    };
  },
  methods: {
    validateUsername() {
      this.username = this.username.replace(/\s*/g, "");
      if (this.username.length != 8) this.valid_username = false;
      else this.valid_username = true;

    },
    validatePassword() {
      this.password = this.password.replace(/\s*/g, "");
      if (this.password.length != 6) { this.valid_password = false; }
      else this.valid_password = true;
    },
    login() {
      this.validatePassword(); this.validateUsername();
      if (this.valid_username && this.valid_password) {
        this.showLoading = true;
        this.$store
          .dispatch("user/login", { userid: this.username, password: this.password })
          .then(() => {
            if (this.$route.query.redirect) {
              this.$router.push(this.$route.query.redirect)
            } else {
              this.$router.push("/");
            }
            this.showLoading = false;
            Message({
              message: "欢迎回来",
              type: "success",
              duration: 1000,
            });
          })
          .catch(() => {
            this.showLoading = false;
          });
      } else {
        Message({
          message: "用户名或密码有误！",
          type: "error",
          duration: 2000,
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
  background-color: rgb(251, 251, 251);
}

header {
  position: relative;
  height: 90px;
  background-image: url('@/assets/schoolImg/schoolLogo.png'), url('@/assets/schoolImg/bg-footer.png');
  background-size: 120px auto, cover;
  background-repeat: no-repeat, no-repeat;
  background-position: 20px 5px, 0px 0px;
}

header .title {
  position: absolute;
  margin-left: 50%;
  transform: translateX(-50%);
  bottom: 15px;
  color: white;
  font-size: 32px;
  font-weight: 600;
}

.center {
  position: relative;
  margin-left: 50%;
  transform: translateX(-50%);
  margin-top: 17vh;
  width: 500px;
}

.loading {
  position: absolute;
  z-index: 200;
  bottom: 20px;
  left: 50%;
  transform: translateX(-50%);
}

.background1 {
  position: absolute;
  z-index: -2;
  top: 220px;
  left: 230px;
  width: 300px;
  height: 300px;
  border-radius: 50%;
  background: linear-gradient(150deg,
      rgb(41, 58, 43, 0.6),
      rgb(55, 84, 43, 0.6),
      rgb(83, 132, 91, 0.6));
}

.background2 {
  position: absolute;
  top: -70px;
  left: -100px;
  z-index: -2;
  width: 400px;
  height: 400px;
  border-radius: 50%;
  background: linear-gradient(-30deg,
      rgb(130, 178, 151),
      rgb(175, 200, 162),
      rgb(223, 235, 199));
}

.form {
  margin-left: 50%;
  transform: translateX(-50%);
  padding: 40px;
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
  font-size: 14px;
}

.form-submit {
  display: flex;
  justify-content: space-evenly;
  padding: 8px 0;
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
</style>
