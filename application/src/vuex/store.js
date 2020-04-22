import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);

const state = {
    user: null
};

const getters = {
    initSex(state){
        if (state.user.sex === 1) {
            return "先生"
        } else {
            return "女士"
        }
    }
};

const mutations = {
    initUser(state, user) {
        state.user = user;
        state.user.login = true;
        window.console.log(state.user)
    }
};


export default new Vuex.Store({
    state,
    getters,
    mutations
});