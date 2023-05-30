// Dom7
var $$ = Dom7;

// Framework7 App main instance
var app = new Framework7({
    root: '#app', // App root element
    id: 'io.framework7.testapp', // App bundle ID
    name: 'Framework7', // App name
    theme: 'auto', // Automatic theme detection
    on: {
        pageAfterIn(page) {
            $$('.page-previous').remove();//移除前面的页面以便重新加载vue
        },
        pageBeforeOut() {
            $$('.backdrop-in').click();
            $$('.page-current .infinite-scroll-content').removeClass('infinite');
        },
    },
    view: {
        pushState: pushState,
        pushStateSeparator: '#',
    },
    // cacheIgnoreGetParameters: true,
    // App root data
    data: function () {
        return {
            user: {
                firstName: 'John',
                lastName: 'Doe',
            },

        };
    },
    dialog: {
        // set default title for all dialog shortcuts
        title: '大数据平台',
        // change default "OK" button text
        buttonOk: '确定',
        buttonCancel: '取消',
        usernamePlaceholder: '请输入用户名',
        passwordPlaceholder: '请输入密码',
        preloaderTitle: '',
        progressTitle: ''
    },
    calendar: {
        monthNames: ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12'],
        dayNames: ['周日', '周一', '周二', '周三', '周四', '周五', '周六'],
        dayNamesShort: ['周日', '周一', '周二', '周三', '周四', '周五', '周六'],
        toolbarCloseText: '确定',
        headerPlaceholder: '选择日期',
        toolbar: true,
        routableModals: false,
    },
    sheet: {
        backdrop: true,
    },
    // App routes
    routes: routes,

});

function navigate(url, data, options) {
    if (data) {
        for (var i in data) {
            url += data[i] + '/';
        }
    }
    mainView.router.navigate(url, options);
}
// Vue.config.delimiters = ['@{', '}']
var rVue = new Vue({
    data: {
        _userInfo:null,
        msg: {
            date: '2019-10-01 ~ 2019-10-10',//校正期间
            sText: '本次未校正',//校正状态
        },
        showLoading: false,
        checked: false,
        inChecking: false,
        startedPlan: false,
        oScollTop:null,
        loading:false,
    },
    computed:{
        userInfo:{
            get:()=>{
                return rVue.$data._userInfo||ltSession.getItem('userInfo')
            },
            set:v=>{
                rVue.$data._userInfo=v
                ltSession.setItem('userInfo',v)
            }
        },
    },
    watch: {
        showLoading(v) {
            if (v) app.preloader.show();
            else app.preloader.hide();
        },
    },
    methods: {
        /**
         * 提示消息
         * @param text 消息内容
         * @param classN 消息class
         * @param pos 位置,默认center,可选top,bottom
         */
        tips(text,classN,pos){
            text=text||'操作成功';
            classN=classN||'';
            pos=pos||'center';
            let long=1000;
            if(pos=='top')long=2500;
            app.toast.show({
                text: text,
                position: pos,
                cssClass:classN,
                closeTimeout: long,
            });
        },
        errTip(text){
          this.tips(text,'bg-color-red','top');
        },
    }
});
// Init/Create main view

var mainView = app.views.create('.view-main', {
    url: '/login/',  //此处起作用的前提是.view-main下为空
    // iosSwipeBack:false
});

