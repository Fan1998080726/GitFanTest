//region 兼容部分ios手机input失焦后页面上移问题
(function() {
    let myFunction
    let isWXAndIos = isWeiXinAndIos()
    if (isWXAndIos) { // 既是微信浏览器 又是ios============（因为查到只有在微信环境下，ios手机上才会出现input失去焦点的时候页面被顶起）
        document.body.addEventListener('focusin', () => { // 软键盘弹起事件
            clearTimeout(myFunction)
        })
        document.body.addEventListener('focusout', () => { // 软键盘关闭事件
            clearTimeout(myFunction)
            myFunction = setTimeout(function() {
                window.scrollTo({top: 0, left: 0, behavior: 'smooth'})// 重点  =======当键盘收起的时候让页面回到原始位置
            }, 200)
        })
    }
})();
function isWeiXinAndIos(m) {
    // window.navigator.userAgent属性包含了浏览器类型、版本、操作系统类型、浏览器引擎类型等信息，这个属性可以用来判断浏览器类型
    let ua = '' + window.navigator.userAgent.toLowerCase()
    // 通过正则表达式匹配ua中是否含有MicroMessenger字符串且是IOS系统
    let isWeixin = /MicroMessenger/i.test(ua) // 是在微信浏览器
    let isIos = /\(i[^;]+;( U;)? CPU.+Mac OS X/i.test(ua) // 是IOS系统
    m=m||3
    if(m==1)return isWeixin
    if(m==2)return isIos
    if(m==3)return isIos && isWeixin
}
if(!dev&&!isWeiXinAndIos(1))location.href='https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx8db'
//endregion
Date.prototype.Format = function (fmt) { //author: meizz
    fmt=fmt||'yyyyMMdd'
    var o = {
        "M+": this.getMonth() + 1,                 //月份
        "d+": this.getDate(),                    //日
        "h+": this.getHours(),                   //小时
        "m+": this.getMinutes(),                 //分
        "s+": this.getSeconds(),                 //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds()             //毫秒
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

$$.fn.scrollEnd = function (callback, timeout) {
    $$(this).scroll(function () {
        var $this = $$(this);
        if ($this.data('scrollTimeout')) {
            clearTimeout($this.data('scrollTimeout'));
        }
        $this.data('scrollTimeout', setTimeout(callback, timeout));
    });
}


var ajax_mKey = null

var keys = []
for (var i = 0; i < 256; i++) {
    keys[i] = Math.random() * 100000 >> 0;
}
sjcl.random.addEntropy(keys);


var ecc = {
    str2base: function (str) {
        if (!sjcl) {
            return '请先引用sjcl.js';
        }
        return sjcl.codec.base64.fromBits(sjcl.codec.utf8String.toBits(str));
    },
    base2str: function (b64) {
        if (!sjcl) {
            return '请先引用sjcl.js';
        }
        return sjcl.codec.utf8String.fromBits(sjcl.codec.base64.toBits(b64));
    },
    randomHex: function (l) {
        if (!sjcl) {
            return '请先引用sjcl.js';
        }
        return sjcl.codec.hex.fromBits(sjcl.random.randomWords(l));
    },
    /**
     * sha256加密
     * @param str 需要加密的string
     * @returns {* | string}
     */
    sha256hash: function (str) {
        if (!sjcl) {
            return '请先引用sjcl.js';
        }
        return sjcl.codec.hex.fromBits(sjcl.hash.sha256.hash(str));
    },
    /**
     * 获取一个elGamal Key
     * @returns {*}
     */
    getLocalGKey: function () {
        if (!sjcl) {
            return '请先引用sjcl.js';
        }
        var CKEY = sjcl.ecc.elGamal.generateKeys(256);

        CKEY.pk64 = this.str2base(JSON.stringify(CKEY.pub.serialize()));
        CKEY.sk64 = this.str2base(JSON.stringify(CKEY.sec.serialize()));
        return CKEY;
    },
    /**
     * 获取一个ecdsa Key
     * @returns {*}
     */
    getLocalEKey: function () {
        if (!sjcl) {
            return '请先引用sjcl.js';
        }
        var CKEY = sjcl.ecc.ecdsa.generateKeys(256);
        CKEY.pk64 = this.str2base(JSON.stringify(CKEY.pub.serialize()));
        CKEY.sk64 = this.str2base(JSON.stringify(CKEY.sec.serialize()));
        return CKEY;
    },
    /**
     * 反序列化获取到的pubKey
     * @param key 获取到的pubKey
     * @returns {*}
     */
    deserializePubKey: function (key) {
        if (!sjcl) {
            return '请先引用sjcl.js';
        }
        return sjcl.ecc.deserialize(JSON.parse(this.base2str(key)));
    },
    /**
     * 数据加密
     * @param dhKey 自己的用于加密的dhKey
     * @param data 需要进行签名的数据，类型:object
     * @returns {*|string}
     */
    encryptData: function (dhKey, data) {
        if (typeof (data) != 'object') {
            console.error('data应为object');
            return;
        }
        return this.str2base(sjcl.encrypt(dhKey, JSON.stringify(data)));
    },
    decryptData: function (dhKey, data) {
        if (typeof (data) != 'string') {
            console.error('data应为string');
            return;
        }
        return JSON.parse(sjcl.decrypt(dhKey, this.base2str(data)));
    },
    /**
     * 数据签名
     * @param gkey 自己的用于加密的Gkey
     * @param data 需要进行签名的数据，类型:object
     * @returns 签名后的数据
     */
    signData: function (gkey, data) {
        return sjcl.codec.base64.fromBits(gkey.sec.sign(data))
    },
    /**
     * 获取aes加密key
     * @param gkey 自己的用于加密的Gkey
     * @param pKey 服务器的publicKey
     * @returns key 加密key
     */
    getDhKey: function (gkey, pKey) {
        var dhKey = ecc.deserializePubKey(pKey);
        return gkey.sec.dh(dhKey);
    },
    getCatPubKey:function (cpk) {
        return   new sjcl.ecc.elGamal.publicKey(sjcl.ecc.curves.c256, sjcl.codec.base64.toBits(cpk));
    },
}
ajax_mKey = ecc.getLocalEKey();


/**
 * 左补位
 * @param str 需要补位的字符串
 * @param len 补后的长度
 * @param pstr 使用什么进行补位
 * @return {*}
 */
function lpad(str, len, pstr) {
    if (!str) return;
    if (str.toString().length >= len) return str;
    pstr = pstr || '0';
    len = len || 2;
    for (var i = 0; i <= len; i++) {
        str = pstr + str;
    }
    return str.substr(0 - len);
}

var windowHeight = window.innerHeight
    || document.documentElement.clientHeight
    || document.body.clientHeight;

Vue.config.productionTip = false;
Vue.config.devtools = false;
//控制台输出
mLog=function(...params){
    if(!dev)return //api
    let extraMsg=''
    if(event) {
        if (event.detail) extraMsg = '###name:' + event.detail.name
        if (event.target.requestUrl) extraMsg = '###url:' + event.target.requestUrl
        // console.log(...params,extraMsg)
    }
    console.groupCollapsed(...params,extraMsg)
    console.trace()
    console.groupEnd()

}
ltSession={
    setItem(key,value){
        return sessionStorage.setItem(key,Base64.encode(JSON.stringify(value)))
    },
    getItem(key){
        let data=sessionStorage.getItem(key)
        if(!data)return null
        return JSON.parse(Base64.decode(data))
    },
}
const timeOut=60000  //接口请求超时
validator={
    checkPhone(phoneNum) {
        if(!phoneNum)return '号码为空'
        phoneNum+=''
        if(phoneNum.length==11 && phoneNum.indexOf('-')<0){
            if(!/(1[3-9])\d{9}/.test(phoneNum))return '手机号码格式不正确'
            else return true
        }else{
            if(!/(^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/.test(phoneNum))return '格式不正确,应为"区号-直拔号码-分机号"'
            else return true
        }
    },
    /*检查身份证号*/
    checkID(id,sex){
        if(!id)return false
        id=id.trim()
        if(id.length!=15&&id.length!=18)return '长度不对，应为15位或18位'
        id=id.toLocaleUpperCase()
        if(!/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/.test(id)) return '应全为数字或最后一位为xX'
        if(id.length==15)id=id.slice(0,6)+'19'+id.slice(6)
        const city={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江 ",31:"上海",32:"江苏",
            33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北 ",43:"湖南",44:"广东",45:"广西",46:"海南",
            50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏 ",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",
            81:"香港",82:"澳门",91:"国外 "};
        if(!city[id.slice(0,2)])return '地区编码不正确'
        const date=id.substr(6,4)+'-'+id.substr(10,2)+'-'+id.substr(12,2)
        if(!isNaN(date)||isNaN(Date.parse(date))) return '出生日期格式不正确'
        if(sex && id.substr(16,1)*1%2!=sex*1%2)return '性别与所选性别不一致'
        if(id.length==18){
            let sum=0
            const jyw='10X98765432'
            for(let i in id){
                if(i==17)break
                sum+=id[i]*((2**(17-i))%11)
            }
            if(id.slice(-1)!=jyw[sum%11])return '校验码不正确'
        }
        return true
    }
}
var baseMixin = {
    data () {//注入属性
        return {
            tVue:null,
            dev:dev,
            params: null,
            /**
             * :{"ID":2,"UNAME":"测试账号","LOGIN":"admin_test","ROLEID":100,"ORGNAME":"四川省","ORGID":"51"}}
             * @property ROLEID
             */
            userInfo: null,
            CurrentPlan:null,
            diclist :{
                schoolType: {401:'部属院校',402:'地方普通本科高校',403:'高职院校', 404:'中职学校',405:'普通高中',406:'初中',407:'小学',408:'幼儿园',409:'特殊教育学校',411:'成人高校'},//学校类型
                notSchool:[[404,405,406,407,408,409],[404,405,406,407,408,409]],//非学校，学校类型开始下标0=>区县，1=>市州
                schoolProp: { //办学性质
                    1:'公办',
                    2:'民办'
                },
                schoolType3: { 2:'小学' , 4:'初中', 6 :'九义校' , 8 :'普通高中' ,12 :'高完中' , 14 : '十二一贯校' },
                progressColors: [
                    {color: '#E6A23C', percentage: 99.9},
                    {color: '#5cb87a', percentage: 100}
                ],
                m_auditStatus_qx:{0:'全部',1:'未上报',2:'待审核', 4:'待市审核',3:'驳回',5:'通过'},
                m_auditStatus_city:{0:'全部',1:'未填报',2:'已填报'},

                labelListStd:['合计','内地学生数','港澳台学生数','留学生数'],
                labelListStdWH:['湖北籍学生数','武汉籍学生数'],
                labelListTea:['合计','内地教职工数','港澳台教职工数','外籍教职工数','离退休教职工数'],

                illType:{1:'确诊病例', 2:'疑似病例'},//病情
                patientTypeMain:{1:'学生', 2:'教职工'},//,人员类别
                patientType:[['内地学生','港澳台学生','留学生'],['内地教职工','港澳台教职工','外籍教职工','离职退休教职工']],//病人
                sexType:{1:'男',2:'女'},
                treatType:{1:'治疗中',2:'治愈出院',3:'死亡',4:'排除疑似',5:'转入确诊病例',6:'其他'},//治疗情况

                subDepartsType:{1:'高校',2:'区县',3:'市州'},
                compareList:{'602101':'春节期间留校学生情况',
                    '602201':'医学隔离病例情况-学生总数',
                    '602241':'医学隔离病例情况-教职工总数',
                    '602301':'发热情况-学生总数',
                    '602341':'发热情况-教职工总数',
                    '602401':'疑似病例-学生总数',
                    '602441':'疑似病例-教职工总数',
                    '602501':'确诊病例-学生总数',
                    '602541':'确诊病例-教职工总数',
                    '602601':'死亡病例-学生总数',
                    '602641':'死亡病例-教职工总数',
                    '602701':'正在实习学生情况'
                },
            },
            oScollTop:null,//原top
            canGetData:true,
            noMore:false,
            mainList:null,
            currentPage:0,
        }
    },
    computed:{
        currentPlan:{
            get:()=>{
                return this.CurrentPlan||ltSession.getItem('currentPlan')
            },
            set:v=>{
                this.CurrentPlan=v
                ltSession.setItem('currentPlan',v)
            }
        },
    },
    created: function () {
        if (this.$parent) {
            return;
        }
        // this.getParams();//获取页面参数
        this.userInfo =rVue.userInfo
        rVue.oScollTop=null

        const mRoute=mainView.router.currentRoute.route
        if(!mRoute||mRoute.name=='login'||mRoute.name=='404')return

        if(!this.userInfo){
            app.dialog.alert('当前还未登录，请登录',null,()=> {
                navigate('/login/')
            })
            return
        }
        if(!mRoute||!mRoute.role)return
        const role=2**(this.userInfo.role*1)
        if((role & mRoute.role*1)==0){
            app.dialog.alert('您没有权限访问此页面，点击确定返回','错误',()=> {
                history.back()
            })
            return
        }
    },
    mounted(){
    },
    filters: {
        dateText: function (time, format) {
            if (!time) return '';
            format = format || 'yyyy-MM-dd';
            return new Date(time * 1000).Format(format)
        },
        applyStatusText: function (status) {
            var a = {'1': '工作中', '11': '申请', '21': '通过', '22': '驳回', '31': '有效', '32': '无效', '100': '完成'};
            return a[status] || '';
        },
        stuTypeText: function (type) {
            return type == 1 ? '未入学' : '辍学'
        },

    },
    methods: {//注入方法
        /**
         * 地址跳转
         * @param url 需要跳转的地址，同域/lskjl/lkjsdl/,其它http://||https://
         * @param prefix  地址需要参数使用的前缀
         * @param obj 需要传递的参数，存入session中
         */
        goDetail(url,prefix,obj){
            if(!prefix || !obj)mLog('prefix||obj不存在')
            if(prefix&&obj)url+=this.setRtVal(prefix,obj)+'/'
            mainView.router.navigate(url);
        },
        /**
         * 获取数据
         * @param url 请求地址  /url/url  || url:url
         * @param data 请求参数 || null
         * @param operation 是否不使用token
         * @returns {Promise<unknown>}
         */
        fetchData(url,data,operation){
            return this.fetch(url,data,'GET',operation)
        },
        /**
         * 提交数据
         * @param url url 请求地址  /url/url  || url:url
         * @param data data 请求参数 || null
         * @param operation 操作成功时的提示信息
         * @returns {Promise<unknown>}
         */
        postData(url,data,operation){
            app.preloader.show();
            return this.fetch(url,data,'POST',operation).then(res=>{
                if(operation){
                    rVue.tips(operation)
                }
                return res
            })
        },
        /** 外部请勿直接调用，使用fetchData||postData */
        fetch(url,data,type,operation){
            let params={
                headers: {
                    'X-UL-WEBTOKEN': 'X',
                },
                timeout:timeOut,
                dataType:'json',
                method:type,
            }
            if(type=='POST'){
                params.headers['Content-Type']= 'application/json;charset=UTF-8'
                params.contentType='text/plain'
            }

            if(!(operation&&typeof operation=='boolean' && operation==true))params.headers['X-UL-WEBTOKEN']=ltSession.getItem('token')
            data=data||{ _time: 0|Date.now()/10000}
            if(url.indexOf(':')>=0)url='/'+url.replace(/:/g,'\/')
            let urlp=url
            if(!(url.startsWith('http://')||url.startsWith('https://'))) url=API_URL +url
            params.url=url+json_b64(data,'/')
            if(type=='POST'){
                params.url=url
                params.data=JSON.stringify(data||{})
            }
            return new Promise(function (resolve, reject) {
                app.request({...params,
                    success: function success(res,status,req) {
                        app.preloader.hide();
                        if (res.R !== 200) {
                            if(res.R==509)rVue.errTip('登录过期，请从微信重新进入！')
                            console.error('接口报错：\n\r url：'+url+'\n\r Error：'+res.C)
                            reject(res,res.C || (res.R+' - Unknown Error'))
                        }
                        if(req.getResponseHeader('Token')){
                            ltSession.setItem('token',req.getResponseHeader('Token'))
                            if(dev)alert('token update')//todo 测试
                        }
                        resolve(res.C);
                    },
                    error: function error(xhr, status) {
                        let err=xhr.response
                        app.preloader.hide();
                        rVue.errTip((err=='timeout'?'请求超时':'请求出错')+'，请刷新后重试！')
                        console.error('请求错误：\n\r url：'+url+'\n\r Error：'+err)
                        //reject(new Error('接口错误：\n\r url：'+url+'\n\r Error：'+err));
                    },
                });
            });
        },
        setRtVal(prefix,obj){
            const iv=Math.random().toString(36).slice(2,10)
            const rData=ltSession.getItem('routeData')||{}
            let nData={...rData}
            Object.keys(rData).map(k=>{
                if(k.slice(0,-8)==prefix)delete nData[k]
            })
            nData[prefix+iv]=obj
            ltSession.setItem('routeData',nData)
            return iv
        },
        getRtVal(key){
            const rData=ltSession.getItem('routeData')||{}
            return rData[key]
        },
        inifinate(func,t){
            rVue.oScollTop=null
            const rObjName='.infinite'
            $$(rObjName).scrollEnd(function (e) {
                rVue.loading=true
                let obj = $$(rObjName);
                if(obj[0].scrollHeight<=$$(window).height())return
                if(rVue.oScollTop && rVue.oScollTop>=obj[0].scrollTop)return;
                rVue.oScollTop=obj[0].scrollTop
                if(obj[0].scrollTop+$$(window).height()*5/3+1>=obj[0].scrollHeight){
                    mLog('scroll')
                    func()
                }
            }, 30);
        },
        getRouteParams(prefix,mode){
            let rp=mainView.router.currentRoute.params
            if(mode==false)return rp
            return this.getRtVal(prefix+rp.eparam)
        },
    }
};
mSelect = {
    name: 'mSelect',
    data() {
        return {
            id: 0,
            tSheet:null,
            selIndex:0,
            pList:[],
        }
    },
    template: `
                <div class=" mSelectSheet" :class="'mSelectSheet'+id+(sheetModel?' sheet-modal':'')">
                    <div class="mSelect" :m-select-id="id">
                        <div class="title" v-if="title" v-html="title"></div>
                        <div class="content">
                            <template v-if="pList">
                                <div v-for="(item,index) in pList"  class="list-item" :class="{'selected':item[label]==value}"
                                 v-html="item[label]" v-on:click="onSubmit(item,index)" :key="index"></div>
                            </template>
                            <div v-else class="text-color-gray text-align-center">没有可选项</div>
                        </div>
                    </div>
                </div>
                `,
    props: {
        'list': {required: true},
        'value': {required: true},
        'title': {type:String,default:null},
        'visible': {type: Boolean},
        'sheetModel': {type: Boolean,default:true},
        'label': {type: String,default:'label'},
        'withAll':{type:Boolean,default:false},
        'allLabel': {type: String,default:'全部'},
    },
    watch: {
        value(){this.scrollToView()},
        visible(v){
            if(v) {
                if(!this.tSheet) {
                    let _this = this, mSheet =app.sheet.open('.mSelectSheet' + this.id)
                    this.tSheet = mSheet
                    mSheet.on('closed', function () {
                        _this.$emit('change', null)
                    })
                }
                this.tSheet.open()
                this.scrollToView();
            }
        },
        list(v){
            if(!v){
                this.pList=v
                return
            }
            if(Array.isArray(v)){
                if(this.withAll) {
                    let allObj = {}
                    allObj[this.label] = this.allLabel
                    v.unshift(allObj)
                }
                mLog(v[0],typeof v[0])
                if(typeof v[0]=='string'){
                    let list=[]
                    v.forEach((v,k)=>{
                        let obj={key:k}
                        obj[this.label]=v
                        list.push(obj)
                    })
                    v=list
                }
                this.pList=v
            }else{
                let list=[]
                if(this.withAll)v[0]=this.allLabel
                Object.keys(v).map(k=>{
                    let nObj={}
                    nObj[this.label]=v[k]
                    nObj['key']=k
                    list.push(nObj)
                })
                this.pList=list
            }
        }
    },
    mounted() {
        this.id = $$('.mSelect').length + lpad(parseInt(Math.random() * 100));
        // console.log($$('.mSelect .content').height());
    },
    methods: {
        scrollToView(){
            this.$nextTick(()=>{
                let obj = $$("div[m-select-id='" + this.id + "'] .content .selected")
                if (obj.length > 0) {
                    // console.log($("div[m-select-id='" + this.id + "'] .content").offset().top);
                    let top = obj.offset().top - obj.parent(0).offset().top - obj.height() * 2;
                    obj.parent(0).scrollTop(top);
                }
            })
        },
        /**
         * 选择后触发，若是直接关闭，回调参数为null,回调后需要主动设置list为null
         * @param obj
         * @param idx
         */
        onSubmit: function (obj, idx) {
            obj['index'] = idx;
            if(this.sheetModel)this.tSheet.close()
            let objx=$$("div[m-select-id='" + this.id + "'] .content div")
            objx.removeClass('selected')
            objx.eq(idx).addClass('selected')
            this.scrollToView()
            // this.pList=[]
            // this.value=obj[this.label]
            this.$emit('change', obj)
        },
    },
};

var mTextarea = {};
var mTextarea = {
    name: 'mTextarea',
    props: {
        // 'value': {required: true},
        title: {required: true},
        maxlength:{default:100,type:Number},
        value:{type:String,default:''},
    },
    data() {
        return {
            id: 0,
            // value:''
        }
    },
    template: `<div class="list mTextarea"><div class="item-content item-input">
      <div class="item-inner">
        <div class="item-title item-label">{{title}}</div>
        <div class="item-input-wrap">
          <textarea placeholder="请输入" v-model="value" :maxlength="maxlength"></textarea>
           <span class="input-clear-button"></span>
        </div>
      </div>
    </div>
    <div style="margin: 5px 0 10px;" class="color-grey1 fontSize12">最多{{maxlength}}字,已输入{{value.length}}字</div>
   <div class="comBtnBox rowFlexBox flexJustifyContentSB">
    <div class="button button-large midBtn bgc-green1 noShadow" style="width: 48%;" v-on:click="cancel">取消</div>
    <div class="button button-large midBtn button-fill" style="width: 48%;" v-on:click="onSubmit">确定</div>
   </div>
</div>`,
    mounted() {
        this.id = $$('.mTextarea').length + lpad(parseInt(Math.random() * 100));
    },
    methods: {
        cancel: function () {
            this.$emit('close')
        },
        onSubmit: function () {
            var obj = {
                label: this.value,
            }
            this.$emit('change', obj)
        }
    },
};
var mInput = {};
var mInput = {
    name: 'mInput',
    props: {
        // 'value': {required: true},
        title: {type: String, required: true},
        unit: {type: String},
        value: {type: String,default:''},
        maxlength:{default:50,type:Number},
        type:{default:'text',type:String},
        maxNumber:{default:0,type:Number},
        minNumber:{default:0,type:Number},
    },
    data() {
        return {
            id: 0,
            canSub:true,
            tips:'',
            // value:''
        }
    },
    template: `<div class="list mInput"><div class="item-content item-input">
      <div class="item-inner">
        <div class="item-title item-label">{{title}}</div>
        <div class="item-input-wrap">
          <input :type="type" placeholder="请输入" v-model="value" :maxlength="maxlength" :style="{'padding-right':unit?'20px':''}">
           <span class="input-clear-button" :style="{'right':unit?'40px':''}"></span>
           <span class="color-grey1 fontSize17" style="line-height: 50px;width: 40px;" v-if="unit">{{unit}}</span>
        </div>
      </div>
    </div>
    <div style="margin: 5px 0;" class="color-grey1 fontSize12">{{tips}}</div>
   <div class="comBtnBox rowFlexBox flexJustifyContentSB">
    <div class="button button-large midBtn bgc-green1 noShadow" style="width: 48%;" v-on:click="cancel">取消</div>
    <div class="button button-large midBtn button-fill" style="width: 48%;" v-on:click="onSubmit" :class="{'color-gray':!canSub}">确定</div>
   </div>
</div>`,
    watch:{
        value(v){
            if(this.type=='number'){
                this.tips=`仅可输入大于`+this.minNumber+(this.maxNumber>0&&this.maxNumber>this.minNumber?`小于${this.maxNumber}`:'')+'的整数'
                if ((this.value * 1 < this.minNumber) || (this.maxNumber > 0 && this.maxNumber > this.minNumber && this.value * 1 > this.maxNumber)) this.canSub = false
                else this.canSub = true
                if(/\./g.test(this.value))this.canSub=false
            }
            else if(this.maxlength*1>0) this.tips=`最多${this.maxlength}字`+(this.value.length>0?`,已输入${this.value.length}字`:'')
        }
    },
    mounted() {
        this.id = $$('.mInput').length + lpad(parseInt(Math.random() * 100));

    },
    methods: {
        cancel: function () {
            this.$emit('close')
        },
        onSubmit: function () {
            var obj = {
                label: this.value,
            }
            this.$emit('change', obj)
        }
    },
};

var mDatepick = {};
var mDatepick = {
    name: 'mDatepick',
    props: {
        // 'value': {required: true},
        title: {type: String, required: true},
        unit: {type: String},
        mode: {type: Number},
    },
    data() {
        return {
            id: 0,
            startDate: '',
            endDate: '',
            // value:''
        }
    },
    template: `<div class="mDatepick list no-hairlines-md">
   <div class="block-title">{{title}}</div>
   <div class="item-content item-input" v-if="mode==1">
      <div class="item-inner">
          <div class="item-title item-label">请选择日期<span class="text-color-red">*</span></div>
        <div class="item-input-wrap">
          <input type="month" placeholder="请输入" v-model="startDate">
        </div>
      </div>
    </div>
<div class="item-content item-input"  v-if="mode==2">
      <div class="item-inner">
          <div class="item-title item-label">生效日<span class="text-color-red">*</span></div>
        <div class="item-input-wrap">
          <input type="date" placeholder="请输入" v-model="startDate">
        </div>
      </div>
    </div>
    <div class="item-content item-input"  v-if="mode==2">
      <div class="item-inner">
                <div class="item-title item-label">过期日<span class="text-color-red">*</span></div>
        <div class="item-input-wrap">
          <input type="date" placeholder="请输入" v-model="endDate">
        </div>
      </div>
    </div>
   <div class="comBtnBox rowFlexBox flexJustifyContentSB">
    <div class="button button-large midBtn bgc-green1 noShadow" style="width: 48%;" v-on:click="cancel">取消</div>
    <div class="button button-large midBtn button-fill" style="width: 48%;" v-on:click="onSubmit">确定</div>
   </div>
</div>`,
    mounted() {
        this.id = $$('.mDatepick').length + lpad(parseInt(Math.random() * 100));
    },
    methods: {
        cancel: function () {
            this.$emit('close')
        },
        onSubmit: function () {
            if (this.mode == 1) {
                var sDate = new Date(this.startDate).Format('yyyyMM')
                var obj = {
                    label: sDate,
                }
            } else if (this.mode == 2) {
                var sDate = new Date(this.startDate).Format('yyyyMMdd')
                var eDate = new Date(this.endDate).Format('yyyyMMdd')
                var obj = {
                    label: sDate + '-' + eDate,
                }
            }
            this.$emit('change', obj)
        }
    },
};

var mSchoolSelect = {};
var mSchoolSelect = {
    name: 'mSchoolSelect',
    props: {
        // 'value': {required: true},
        title: {type: String, required: true},
        unit: {type: String},
        mode: {type: Number},
    },
    data() {
        return {
            id: 0,
            startDate: '',
            endDate: '',
            // value:''
        }
    },
    template: `<div class="mSchoolSelect">
  
</div>`,
    mounted() {
        this.id = $$('.mSchoolSelect').length + lpad(parseInt(Math.random() * 100));
    },
    methods: {
        cancel: function () {
            this.$emit('close')
        },
        onSubmit: function () {
            this.$emit('change', obj)
        }
    },
};

var areaSelect = {};
var areaSelect = {
    name: 'areaSelect',
    mixins: [baseMixin],
    data() {
        return {
            id: 0,
        }
    },
    template: `<div class="areaSelect" :m-select-id="id">
                <div class="title" v-html="title"></div>
                <div class="content">
                    <div class="list-item" v-for="(item,index) in list" :class="{'selected':item.value==value}"
                        v-html="item.label" v-on:click="onSubmit(item,index)"></div>
                </div>
            </div>`,
    props: {'list': {required: true}, 'value': {required: true}, 'title': {required: true}, 'refresh': {type: Boolean}},
    watch: {
        refresh(v) {
            let obj = $$("div[m-select-id='" + this.id + "'] .content .selected");
            if (obj.length > 0) {
                console.log(obj)
                let top = obj.offset().top - obj.parent(0).offset().top - obj.height() * 2;
                obj.parent(0).scrollTop(top);
            }
        }
    },
    mounted() {
        console.log($$('.areaSelect .content').height());
        this.id = $$('.areaSelect').length + lpad(parseInt(Math.random() * 100));
    },
    methods: {
        onSubmit: function (obj, idx) {
            obj['index'] = idx;
            this.$emit('change', obj)
        }
    },
};
var ltSearchBar={
    template:`
    <div class="mSearchBar" :class="searchBar?'focused':''" @click.self="searchBar=false">
                    <div class="titleBar">
                        <div class="searchbar-input" v-on:click="searchBar=true">
                            <input type="search" class="searchInput" :disabled="disableInput" v-bind:value="value" 
                            v-on:input="$emit('input', $event.target.value)" :placeholder="placeholder" id="searchBarInput">
                            <i class="searchbar-icon"></i>
                            <span class="input-clear-button" @click="clearInput"></span>
                        </div>
                        <span class="cBtn" @click="btnClk(0)" >取消</span>
                    </div>
                    <div class="searchItems ">
                        <slot></slot>
                        <div class="footer">
                            <button class="button button-outline" @click="btnClk(2)">清空</button>
                            <button class="button button-fill" @click="btnClk(1)">确定</button>
                        </div>
                    </div>
                    <div class="searchbar-backdrop" @click="searchBar=false"></div>
                </div>`,
    data(){return{
            searchBar:false,
    }
    },
    props: ["value",'placeholder','disableInput'],
    methods:{
        clearInput(){
            setTimeout(()=>{
                $$('#searchBarInput').blur()
                this.btnClk(1)},30)
        },
        btnClk(m){
            if(m<2)this.searchBar=false
            this.$emit('click',m)
        }
    }
}
ltNumberKeyboard={
    name:'ltNumberKeyboard',
    //退格：10，确定：11
    template:`
        <div class="numberKeyboard" :class="{'active':visible}">
            <div class="col-1">
                <div v-for="i in [1,2,3,4,5,6,7,8,9,'.',0]" v-text="i" @click="cClick(i)" :class="i=='.'&&!dotEnable?'idlast':''"></div>
                <div :class="{'idlast':!idlast}"  @click="cClick(ukey)">{{ukey}}</div>
            </div>
            <div class="col-2">
                <div  @click="cClick(10)"><span class="backspace">×</span></div>
                <div class="ascertain" @click="cClick(11)"><span>确定</span></div>
            </div>
        </div>
    `,
    data(){return{
    }
    },
    props: {
        isId:{type:Boolean,default:false},
        idlast:{type:Boolean,default:false},
        visible:{type:Boolean,default:false},
        ukey:{type:String,default:''},
        dotEnable:{type:Boolean,default:true},
    },
    methods:{
        cClick(i){
            this.$emit('type',i)
        }
    },
}
ltNumber = {
    name: 'ltNumber',
    components: {
        'ltNumberKeyboard': ltNumberKeyboard,
    },
    template: `
                <div class=" popup" :class="'mSelectSheet'+id">
                    <div class="numberInput" :class="{'active':active}">
                        <div class="title" v-if="title">{{title}}</div>
                        <div class="input" :placeholder="placeholderx" v-on:click="active=true">{{value}}<span class="light"></span><span class="clear" v-if="value.length>0" @click="clear">×</span></div>
                        <div class="tips">{{tips}}</div>
                        <div class="footer">
                            <button class="button button-outline" @click="btnClk(2)">取消</button>
                            <button class="button button-fill" @click="btnClk(1)">确定</button>
                        </div>
                    </div>
                    <lt-number-keyboard v-on:type="input" :visible="active" :ukey="ukey" :dot-enable="dotEnable" :idlast="idlast" />
                </div>
                `,
    data() {
        return {
            id: 0,
            active:false,
            tips:'',
            ukey:'',
            tSheet:null,
            placeholderx:'请输入',
            dotEnable:true,
            idlast:false,
        }
    },
    props:{
        title:{type:String,default:null},
        value:{type:Number,default:''},
        placeholder:{type:String,default:''},
        type:{type:String,default:'number'},//仅number,int,id,tel
        min:{type:Number,default:0},
        max:{type:Number,default:null},
        maxlength:{type:Number,default:20},
        visible:{type:Boolean},
    },
    watch: {
        placeholder(v){
            this.placeholderx=v
        },
        type(v){
            if(v!=='number'){
                this.ukey=''
                if(this.type=='tel') {
                    this.ukey='-'
                    this.idlast=true
                }
                if(this.type=='id'){
                    this.ukey='X'
                    this.idlast=false
                }
                this.dotEnable=false
            }
            else this.dotEnable=true
            if((v=='int'||v=='number')&&this.min*1<0){
                this.idlast=true
                this.ukey='-'
            }
        },
        visible(v){
            if(v) {
                this.active=v
                this.value+=''
                if(this.value.length>0)this.placeholderx=''
                else this.placeholderx=this.placeholder||'请输入'
                if(!this.tSheet) {
                    let _this = this, mSheet =app.popup.open('.mSelectSheet' + this.id)
                    this.tSheet = mSheet
                    mSheet.on('closed', function () {
                        _this.$emit('change', null)
                    })
                }
                this.tSheet.open()
                if(this.type=='int'||this.type=='number'){
                    this.tips='仅可输入大于'+this.min
                    if(this.max)this.tips+=',小于'+this.max
                    if(this.type=='int')this.tips+='的整数'
                    else this.tips+='的数 '
                }
                if(this.max)return
                if(this.maxlength)this.tips+=' 最长可输入'+this.maxlength+'个字'
                if(this.value.length>0)this.tips+=',当前已输入'+this.value.length+'个字'
            }
        },
    },
    mounted() {
        this.id = $$('.mltNumber').length + lpad(parseInt(Math.random() * 100));
        this.value+=''
        if(this.value.length==0)this.placeholderx=this.placeholder
    },
    methods:{
        clear(){
            this.value=''
            this.input(10)
            this.placeholderx=this.placeholder
        },
        input(txt){
            this.value+=''
            if(txt==11){
                this.active=false
                this.btnClk(1)
                return
            }
            if(txt==10){
                this.value=this.value.slice(0,-1)
                if(this.maxlength>0 && !this.max){
                    if(this.tips.indexOf(',当前')>0)this.tips=this.tips.slice(0,this.tips.indexOf(',当前'))+',当前已输入'+this.value.length+'个字'
                    else this.tips+=',当前已输入'+this.value.length+'个字'
                }
                if(this.value.length==0)this.placeholderx=this.placeholder
                if(this.type=='id') {
                    if (this.value.length == 17) this.idlast = true
                    else this.idlast = false
                }
                return
            }
            if(this.maxlength>0 && this.value.length>=this.maxlength)return;
            if((this.type=='int'||this.type=='number')&&txt=='-'&&this.value.length>0)return;
            if(['int','id','tel'].indexOf(this.type)>=0&&txt=='.')return;
            if(['int','id'].indexOf(this.type)>=0&&this.value=='0'&&this.value.length==1){
                if(txt=='0')return;
                else this.value=this.value.substr(1)
            }
            if((this.value=='' || this.value.indexOf('.')>0) && txt=='.' && this.type=='number')return;
            if(txt=='-' && this.type=='tel' && this.value.endsWith('-'))return;
            if(this.type=='int' && txt=='.')return;
            if(this.type=='id'){
                if(this.value.length==16)this.idlast=true
                else this.idlast=false
                if(this.value.length==18)return;
            }
            if((this.max||this.max==0||this.max==='0') && (this.value+txt)*1>this.max)return
            if(this.min && (this.value+txt)*1<this.min)return
            this.value+=txt
            if(this.value.length>0 && this.placeholderx.length>0)this.placeholderx=''
            if(this.maxlength>0){
                if(this.max)return
                if(this.tips.indexOf(',当前')>0)this.tips=this.tips.slice(0,this.tips.indexOf(',当前'))+',当前已输入'+this.value.length+'个字'
                else this.tips+=',当前已输入'+this.value.length+'个字'
            }
        },
        btnClk(v){
            this.active=false
            this.placeholder=''
            this.tSheet.close()
            this.tips=''
            if(v==2){
                this.$emit('change',null)
                this.value=''
                return
            }
            this.$emit('change',this.value)
            // this.value=''
        },
    }
};
ltProgress={
    name:'ltProgress',
    template:`
       <div class="mProgress">
            <div class="currentProgrss" :style="{'width':percent+'%','background-color':cColor}">
                <div class="innert-text">
                    {{current}}/{{total}}
                </div>
            </div>
        </div>`,
    data(){
        return{
            percent:0,//当前百分比
            text:'',
            cColor:'yellow',//当前颜色
        }
    },
    props:{
        colors:{type:Object,default:{0:'#e6a23c',100:'green'}},
        current:{type:Number,default:0},
        total:{type:Number,default:0},
    },
    watch:{
        current(v){
            this.set()
        },
        total(v){
            this.set()
        }
    },
    mounted(){
        setTimeout(()=>this.set(),10)

    },
    methods:{
        set(){
            if(this.total>0)this.percent=this.current*1/this.total*1*100
            else this.percent=0
            if(this.percent>100)this.percent=100
            const colorKeys=Object.keys(this.colors)
            let cColor=''
            for(i of Object.keys(this.colors)){
                if(this.percent>=i*1)cColor=this.colors[i]
                else break
            }
            this.cColor=cColor
            if(this.percent<20)this.percent=20
        }
    }
}
fillAbout={
    name:'fillAbout',
    template:`
      <div class="zbjdC">
    <!--    <img style="width: 100%;margin-bottom: -4px;" v-for="i in 7" :src="'./images/fillDes/'+i.toString().padStart(2,0)+'.jpg'" alt="">-->
    <h3>新型冠状病毒感染的肺炎疫情防控填报数据指标解释</h3>
    <div>1.所有学校分为“部属高校”“地方普通本科高校”“高职院校”“成人本科”“中职学校”“普通高中”“初中”“小学”“幼儿园”“特殊教育学校”，按照教育事业年度统计口径确定学校性质。</div>
    <div>2.高校所在地按照高校本部所在地填写。</div>
    <div>3.所有学生类型分为“内地学生数”“港澳台学生数”“留学生数”；教职工类型分为“内地教职工数”“港澳台教职工数”“外籍教职工数”“离退休教职工数”。</div>
    <div>4.“在校生总数（事业年报数）”填写2019年事业年报已报的固定数据。“教职工总数（事业年报数）”填写2019年事业年报已报的固定数据，其中，教职工分学段按照就高学段原则分类统计。</div>
    <div>5.“在校学生情况（校内）”指当天在校且需学校落实防控责任的学生人数。在校学生数（校内）=春节期间留校学生数+提前返校学生数。<span class="zbjd_yb">其中留学生数与报送国际合作交流处的数据口径一致。</span></div>
    <div>6.“在校教职工情况（校内）”指由学校落实防控责任的教职工人员已经在校内的数量，包含需学校落实防控责任的家属区教职工含教师子女。同时包含在编在职人员、合同人员、长期聘用人员、临时聘用人员、离退休人员。（若存在外包劳务人员，以外包劳务合同人员为准，暂未签订的不纳入。）</div>
    <div>7.“春节假期留校学生”指春节期间登记留校且当日依然在校的学生数量，此数据只减不增，减少人员需做好管理台账登记工作。</div>
    <div>8.“当前返校学生”填报口径进一步明确，指1月24日以后，即春节后因特殊情况返校的学生人数，不含从外地返回家属区内居住的学生。</div>
    <div>9.“新型冠状病毒感染的肺炎疑似病例”指由卫生疾控部门或当地定点收治医疗机构确认的病例。</div>
    <div>10.“新型冠状病毒感染的肺炎确诊病例”指由卫生疾控部门或当地定点收治医疗机构通报或确诊的病例。</div>
    <div>11.“新型冠状病毒感染的肺炎死亡病例”指由卫生疾控部门或当地定点收治医疗机构确定因新型冠状病毒感染的肺炎死亡的病例。</div>
    <div>12.对9、10、11所确定的疑似、确诊、死亡病例需填报在办事中心下“个体病例明细情况表”端口填报相关信息，每个病例提交一条信息。现已有的三类（疑似、确诊、死亡）病例需一次性补充填报“个体病例明细情况表”，且从2月3日起需每日更新病例明细表。</div>
    <div>13.原来报送的《XXX(单位名称)关于新型冠状病毒感染肺炎确诊病例的情况报告》必须继续填报，第一时间电话联系陈鹏老师，15828536655。</div>
    <div>14.新增“医学隔离病例情况”指学校在籍学生（全口径）中由当地卫生部门确定为“确诊病例密切接触者”并按要求必须进行医学隔离的学生数量和教职工数量。</div>
    <div>15.发热情况统计口径不变。</div>
    <div>16.报送数据统计截止时间为当日报送前最新掌握情况。每日报送截止时间为下午15:00。需每日更新“新型冠状病毒感染的肺炎疫情防控情况汇报表”。以当日统计数据为准，不是填写对比前一天的新增数据。</div>
    <div>17.个体病例表中“病程”按照病例生病时间开始填写，做到每日关怀更新，需与《XXX(单位名称)关于新型冠状病毒感染肺炎确诊病例的情况报告》内容一致。</div>
    <div>18.“其中湖北籍学生”指户籍为湖北籍，不区分是否从湖北返回。“其中武汉籍学生”指户籍为武汉籍，不区分是否从武汉返回。“其中湖北籍学生”应包含“其中武汉籍学生”数据。</div>
  </div>
    `,
}
nomoreData={
    name:'nomoreData',
    template:`
        <div v-if="!list" class="padding text-align-center">
            <div>数据加载中...
                <div class="preloader">
                  <span class="preloader-inner">
                    <span class="preloader-inner-line"></span>
                    <span class="preloader-inner-line"></span>
                    <span class="preloader-inner-line"></span>
                    <span class="preloader-inner-line"></span>
                    <span class="preloader-inner-line"></span>
                    <span class="preloader-inner-line"></span>
                    <span class="preloader-inner-line"></span>
                    <span class="preloader-inner-line"></span>
                    <span class="preloader-inner-line"></span>
                    <span class="preloader-inner-line"></span>
                    <span class="preloader-inner-line"></span>
                    <span class="preloader-inner-line"></span>
                  </span>
                </div>
            </div>
        </div>
        <div v-else-if="list.length==0"  class="dashBoard padding text-align-center"> <div>没有数据</div></div>
        <div v-else-if="noMore" class="noMore"></div>
    `,
    //||(rVue.loading&&!noMore)  加载数据的提示
    props:{
        list:{type:Object,require:true,default:null},
        noMore:{type:Boolean,default:false,require:true},
    },
    watch:{
        list(v){
            rVue.loading=false
        },
        noMore(v){
            rVue.loading=false
        },
    }
}
//vue全局组件

var temp