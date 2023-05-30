//role 2**i 无则全部
routes = [
    {
        path: '/',
        url: './index.html'+mv,
    },
    {
        name: 'login',
        path: '/login/',
        componentUrl: './pages/login.html'+mv,
    },
    {
        name: 'pIndex',
        path: '/pIndex/',
        componentUrl: './pages/mIndex.html'+mv,
    },
    {
        name: 'changePwd',
        path: '/changePwd/',
        componentUrl: './pages/changePwd.html'+mv,
    },
    {
        name: 'index',
        path: '/index/',
        componentUrl: './pages/index.html'+mv,
    },
    {
        name: 'test',
        path: '/test/',
        componentUrl: './pages/test.html'+mv,
    },
    //个体病例
    {
        name: 'single',
        path: '/single/',
        componentUrl: './pages/single.html'+mv,
        routes:[
            {
                name: 'singleAdd',
                path: '/add/',
                componentUrl: './pages/single_ae.html' + mv,
                role:2+4+8
            },
            {
                name: 'singleEdit',
                path: '/edit/:eparam/',
                componentUrl: './pages/single_ae.html' + mv,
                role:2+4+8
            },
            {
                name: 'sitAdd',
                path: '/sit/add/:eparam/',
                componentUrl: './pages/single_sit_add.html' + mv,
                role:2+4+8
            },
            {
                name: 'sitView',
                path: '/sit/view/:eparam/',
                componentUrl: './pages/single_sit_view.html' + mv,
            },
        ]
    },
    //下级填报
    {
        name: 'subFill',
        path: '/subFill/',
        componentUrl: './pages/sub_fill.html'+mv,
        role:4+8+16,
        routes:[
            {
                name: 'subFill_follow',
                path: '/follow/',
                componentUrl: './pages/sub_fill_follow.html' + mv,
                role:4+8+16,
            },
            {
                name: 'subFill_follow_detail',
                path: '/follow/detail/:eparam/',
                componentUrl: './pages/sub_fill_follow_detail.html' + mv,
                role:4+8+16,
            },
            //当前填报汇总
            {
                name: 'subFill_summary',
                path: '/summary/',
                componentUrl: './pages/sub_fill_summary.html' + mv,
                role:4+8+16,
            },
            {
                name: 'subFill_summary_collect',
                path: '/summary/collect/:eparam/',
                componentUrl: './pages/sub_fill_summary_collect.html' + mv,
                role:4+8+16,
            },
            {
                name: 'subFill_summary_detail',
                path: '/summary/detail/:eparam/',
                componentUrl: './pages/sub_fill_summary_detail.html' + mv,
                role:4+8+16,
            },
            //历史填报汇总
            {
                name: 'subFill_history',
                path: '/history/',
                componentUrl: './pages/sub_history.html' + mv,
                role:4+8+16,
            },
            {
                name: 'subFill_history_detail',
                path: '/history/detail/:eparam/',
                componentUrl: './pages/sub_fill_history_detail.html' + mv,
                role:4+8+16,
            },
            {
                name: 'subFill_history_follow',
                path: '/history/follow/:eparam/',
                componentUrl: './pages/sub_fill_follow.html' + mv,
                role:4+8+16,
            },
            {
                name: 'subFill_history_notfill',
                path: '/history/notfill/:eparam/',
                componentUrl: './pages/sub_fill_notfill.html' + mv,
                role:4+8+16,
            },
            {
                name: 'subFill_history_province_plist',
                path: '/history/plist/',
                componentUrl: './pages/subFill_history_province_plist.html' + mv,
                role:4+8+16,
            },
            {
                name: 'subFill_history_province_flist',
                path: '/history/flist/:eparam/',
                componentUrl: './pages/sub_history.html' + mv,
                role:4+8+16,
            },
        ]
    },
    //本级填报
    {
        name: 'fill',
        path: '/fill/',
        componentUrl: './pages/fill.html'+mv,
        role:2+4+8+16,
        routes:[
            {
                name: 'fill_add',
                path: '/add/',
                componentUrl: './pages/fill_add.html' + mv,
                role:2+4+8,
            },
            {
                name: 'fill_history',
                path: '/history/',
                componentUrl: './pages/fill_add.html' + mv,
                role:2+4+8,
            },
            {
                name: 'fill_detail',
                path: '/detail/:eparam/',
                componentUrl: './pages/fill_detail.html' + mv,
                role:2+4+8+16,
            },

        ]
    },
    //下级帐号
    {
        name: 'sub_account',
        path: '/subAccount/',
        componentUrl: './pages/sub_account.html' + mv,
        role:4+8+16,
        routes:[
            {
                name: 'sub_account_add',
                path: '/add/',
                componentUrl: './pages/sub_account_ae.html' + mv,
                role:4+8+16,
            },
            {
                name: 'sub_account_edit',
                path: '/edit/:eparam/',
                componentUrl: './pages/sub_account_ae.html' + mv,
                role:4+8+16,
            },
        ]
    },
    //填报说明
    {
        name: 'fill_des',
        path: '/fillDes/',
        componentUrl: './pages/fill_des.html' + mv,
    },
    //省级数据比对
    {
        name: 'data_compare',
        path: '/compare/',
        componentUrl: './pages/data_compare.html' + mv,
        role:16,
    },

    {
        name: 'stdInfo',
        path: '/stdInfo/',
        componentUrl: './pages/stdInfo.html'+mv,
    },
    {
        path: '/about/',
        url: './pages/about.html',
    },
    {
        path: '/form/',
        url: './pages/form.html',
    },
    {
        name: 'npage',
        path: '/npage/',
        componentUrl: './pages/npage.html',
    },
    // Page Loaders & Router
    {
        path: '/page-loader-template7/:user/:userId/:posts/:postId/',
        templateUrl: './pages/page-loader-template7.html',
    },
    {
        path: '/page-loader-component/:user/:userId/:posts/:postId/',
        componentUrl: './pages/page-loader-component.html',
    },
    {
        path: '/request-and-load/user/:userId/',
        async: function (routeTo, routeFrom, resolve, reject) {
            // Router instance
            var router = this;

            // App instance
            var app = router.app;

            // Show Preloader
            app.preloader.show();

            // User ID from request
            var userId = routeTo.params.userId;

            // Simulate Ajax Request
            setTimeout(function () {
                // We got user data from request
                var user = {
                    firstName: 'Vladimir',
                    lastName: 'Kharlampidi',
                    about: 'Hello, i am creator of Framework7! Hope you like it!',
                    links: [
                        {
                            title: 'Framework7 Website',
                            url: 'http://framework7.io',
                        },
                        {
                            title: 'Framework7 Forum',
                            url: 'http://forum.framework7.io',
                        },
                    ]
                };
                // Hide Preloader
                app.preloader.hide();

                // Resolve route to load page
                resolve(
                    {
                        componentUrl: './pages/request-and-load.html',
                    },
                    {
                        context: {
                            user: user,
                        }
                    }
                );
            }, 1000);
        },
    },
    // Default route (404 page). MUST BE THE LAST
    {
        name:'404',
        path: '(.*)',
        url: './pages/404.html',
    },
];
