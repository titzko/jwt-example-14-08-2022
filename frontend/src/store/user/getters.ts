import { GetterTree } from "vuex";
import {RootState} from "@/store/RootState";
import {UserState} from "@/store/user/types";


export const getters: GetterTree<UserState, RootState> = {
    getUsername(state): string {
        return state.username;
    },
    getJwtToken(state): string {
        return state.jwt;
    },
    getRefreshJwt(state): string {
        return state.refreshJwt;
    },
}


