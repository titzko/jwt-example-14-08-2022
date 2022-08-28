var _a;
export var UserMutations;
(function (UserMutations) {
    UserMutations["SET_USERNAME"] = "SET_USERNAME";
    UserMutations["SET_JWT"] = "SET_JWT";
    UserMutations["SET_REFRESH_JWT"] = "SET_REFRESH_JWT";
})(UserMutations || (UserMutations = {}));
export var mutations = (_a = {},
    _a[UserMutations.SET_USERNAME] = function (state, payload) {
        state.username = payload;
    },
    _a[UserMutations.SET_JWT] = function (state, payload) {
        state.jwt = payload;
    },
    _a[UserMutations.SET_REFRESH_JWT] = function (state, payload) {
        state.refreshJwt = payload;
    },
    _a);
//# sourceMappingURL=mutations.js.map