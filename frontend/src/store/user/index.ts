import {Module} from "vuex";
import {RootState} from "@/store/RootState";
import {UserState} from "@/store/user/types";
import { getters } from "@/store/user/getters";
import {mutations} from "@/store/user/mutations";

const state: UserState = {
    username: "",
    jwt: "",
    refreshJwt: ""
}

export const user: Module<UserState, RootState> = {
    state,
    getters,
    mutations
}