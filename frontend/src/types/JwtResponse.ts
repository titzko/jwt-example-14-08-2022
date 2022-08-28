interface JwtResponse {
    access_token: string,
    refresh_token: string,
    user_name: string;
    valid: boolean;
}

export {JwtResponse};