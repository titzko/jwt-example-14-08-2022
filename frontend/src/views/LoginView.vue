<template>
  <div class="container login-view-component">
    <h1>Login</h1>
    <div class="mb-3">
  <form>
  <div class=" mt-3">
    <label  for="usernameInput" class="form-label">Username</label>
    <input v-model="username" type="text" class="form-control" id="usernameInput" placeholder="Enter username..." required>
  </div>
  <div class=" mt-3">
    <label for="passwordInput" class="form-label">Username</label>
    <input v-model="password" type="password" class="form-control" id="passwordInput" placeholder="Enter password..." required>
  </div>
  <div class=" mt-3 d-flex justify-content-center">
    <button class="btn btn-primary" @click="login">Login</button>
    <button class="btn btn-custom">Create Account</button>
  </div>
  </form>
{{ storeUsername }} <br>
      {{ this.$store.getters.getJwtToken }} <br>

    </div>
  </div>
</template>

<script lang="ts">
import {UserCredentials} from "@/types/UserCredentials";
import {sendUserLogin} from "@/services/login-service";
import {JwtResponse} from "@/types/JwtResponse";
import {UserMutations} from "@/store/user/mutations";

export default {
  name: 'LoginView',
  data() {
    return {
      username: "",
      password: ""
    }
  },
  computed: {

    //just keeping this code here as reference -> im still not sure if i like those mutations
    storeUsername: {
      get(): string {
        return this.$store.getters.getUsername;
      },
      set(value: string): void {
        this.$store.commit(UserMutations.SET_USERNAME, value);
      }
    },
  },
  methods: {
    login(): void {
      const credentials: UserCredentials = {
        username: this.username,
        password: this.password
      };
      sendUserLogin(credentials)
      .then((response) => {
        if(response.valid) {
          this.handleSuccessfullLogin(response as JwtResponse);
        }else {
          this.handleFailedLogin();
        }
      });
    },
    handleSuccessfullLogin(jwtResponse: JwtResponse): void {
      console.log("Successfull Login")
      console.log(jwtResponse);
      this.storeUsername = jwtResponse.user_name;
      this.$store.commit(UserMutations.SET_JWT, jwtResponse.access_token);



      //TODO write in store
      //write util function -> append jwt in header
      //TODO router push
    },
    handleFailedLogin(): void {
      console.log("Unsuccessfull login");
      //TODO tell user he faked up
    }
  }
}
</script>

<style lang="scss">
@import "src/styles/main.scss";

.login-view-component {
  form {
    div {
      button {
        margin-right: 16px;
        width: 200px
      }
    }
  }
}
</style>
