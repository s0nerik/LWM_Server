// Place your Spring DSL code here
beans = {
    oauthUserDetailsService(LWMOauthUserDetailsService) {
        userDetailsService = ref('userDetailsService')
    }
}
