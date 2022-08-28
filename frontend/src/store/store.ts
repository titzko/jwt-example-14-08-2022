import Vue from 'vue'
import Vuex, {StoreOptions} from 'vuex'
import {RootState} from "@/store/RootState";
import { user } from "@/store/user"

Vue.use(Vuex)

const store: StoreOptions<RootState> = {
  modules: {
    user,
  }

}

export default new Vuex.Store<RootState>(store);