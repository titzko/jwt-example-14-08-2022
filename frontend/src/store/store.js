import Vue from 'vue';
import Vuex from 'vuex';
import { user } from "@/store/user";
Vue.use(Vuex);
var store = {
    modules: {
        user: user,
    }
};
export default new Vuex.Store(store);
//# sourceMappingURL=store.js.map