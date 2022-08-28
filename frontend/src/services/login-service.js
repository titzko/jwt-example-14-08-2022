import { __assign, __awaiter, __generator } from "tslib";
export function sendUserLogin(credentials) {
    return __awaiter(this, void 0, void 0, function () {
        return __generator(this, function (_a) {
            return [2 /*return*/, fetch("/api/login", {
                    method: "POST",
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                    body: encodeURI("username=".concat(credentials.username, "&password=").concat(credentials.password)),
                }).then((function (response) {
                    if (response.ok) {
                        return response.json().then(function (data) { return (__assign(__assign({}, data), { valid: true })); });
                    }
                    return { valid: false };
                }))];
        });
    });
}
//# sourceMappingURL=login-service.js.map