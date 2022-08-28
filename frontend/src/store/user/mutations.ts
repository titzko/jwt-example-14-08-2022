import { MutationTree } from "vuex";
import {UserState} from "@/store/user/types";

export enum UserMutations {
    SET_USERNAME = "SET_USERNAME",
    SET_JWT = "SET_JWT",
    SET_REFRESH_JWT = "SET_REFRESH_JWT"
}

export const mutations: MutationTree<UserState> = {
    [UserMutations.SET_USERNAME](state, payload: string) {
        state.username = payload;
    },
    [UserMutations.SET_JWT](state, payload: string) {
        state.jwt = payload;
    },
    [UserMutations.SET_REFRESH_JWT](state, payload: string) {
        state.refreshJwt = payload;
    }
}