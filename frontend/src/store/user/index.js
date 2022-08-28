import { getters } from "@/store/user/getters";
import { mutations } from "@/store/user/mutations";
var state = {
    username: "",
    jwt: "",
    refreshJwt: ""
};
export var user = {
    state: state,
    getters: getters,
    mutations: mutations
};
//# sourceMappingURL=index.js.map