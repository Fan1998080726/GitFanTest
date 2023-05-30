const tempProps={
    601100(XDICLIST,edit){
        const schoolLX=XDICLIST.schoolType
        let pArr={},index=0
        const stdLen=XDICLIST.labelListStd.length,teaLen=XDICLIST.labelListTea.length
        for(let mmkey in schoolLX){
            pArr[mmkey]=[]
            let pInArr=pArr[mmkey],nProp={}
            nProp.title=null
            nProp.list=[]
            nProp.list.push({label:'学校数量',prop:index*1+mmkey.slice(-2,-1)*9991+501091})
            nProp.list.push({label:'在校生总数（事业年报数）',prop:index*1+mmkey.slice(-2,-1)*9991+601001})
            nProp.list.push({label:'教职工总数（事业年报数）',prop:index*1+mmkey.slice(-2,-1)*9991+601011})
            pInArr.push(nProp)

            let fArr=['在校学生情况（校内）','春节期间留校学生情况','返校学生情况（1月24日后起算）']
            fArr.forEach((d,k)=>{
                nProp={list:[]}
                nProp.title=d
                XDICLIST.labelListStd.forEach((lb,ky)=>{
                    let sProp={label:lb}
                    sProp.prop=601101+index*1+mmkey.slice(-2,-1)*9991+ky*10+(k==1?1000:k==2?1800:0)
                    if(ky==0)sProp.edit=false
                    else{
                        // if(ky==1)sProp.maxP=601101+index*1+mmkey.slice(-2,-1)*9991+ky*10+(k==1?1000:k==2?1800:0)
                        sProp.calcs=[
                            {m:'sum',g:601101+index*1+mmkey.slice(-2,-1)*9991+(k==1?1000:k==2?1800:0),b:601110+
                                    index*1+mmkey.slice(-2,-1)*9991+(k==1?1000:k==2?1800:0),t:stdLen,s:10},
                            {m:'sum',g:601101+index*1+ky*10+mmkey.slice(-2,-1)*9991,l:[601101+index*1+ky*10+mmkey.slice(-2,-1)*9991+1000,601101+index*1+ky*10+mmkey.slice(-2,-1)*9991+1800]},
                            {m:'sum',g:601101+index*1+mmkey.slice(-2,-1)*9991,l:[601101+index*1+mmkey.slice(-2,-1)*9991+1000,601101+index*1+mmkey.slice(-2,-1)*9991+1800]}
                        ]
                    }
                    if(k==0)sProp.edit=false
                    nProp.list.push(sProp)
                })
                pInArr.push(nProp)
            })

            nProp={list:[]}
            nProp.title='在校教职工情况（校内）'
            XDICLIST.labelListTea.forEach((lb,ky)=>{
                let sProp={label:lb}
                sProp.prop=601101+index*1+mmkey.slice(-2,-1)*9991+ky*10+40
                if(ky==0)sProp.edit=false
                else{
                    sProp.calcs=[
                        {m:'sum',g:601101+index*1+mmkey.slice(-2,-1)*9991+40,b:601110+ index*1+mmkey.slice(-2,-1)*9991+40,t:stdLen,s:10}
                    ]
                }
                nProp.list.push(sProp)
            })
            pInArr.push(nProp)

            pInArr.push({title:'正在实习学生情况',list:[{label:'合计',prop:index*1+mmkey.slice(-2,-1)*9991+602701,edit:false}]})

            fArr=['省内实习学生数','外省实习学生数']
            fArr.forEach((d,k)=>{
                nProp={list:[]}
                nProp.title='正在实习学生情况- '+d
                let list=k==0?['小计','成都','其他市州']:['小计','湖北','浙江','广东','河南','湖南','其他省市']
                list.forEach((lb,ky)=>{
                    let sProp={label:lb}
                    sProp.prop=602711+index*1+mmkey.slice(-2,-1)*9991+ky*10+(k==1?30:0)
                    if(ky==0)sProp.edit=false
                    else{
                        sProp.calcs=[
                            {m:'sum',g:602711+index*1+mmkey.slice(-2,-1)*9991+(k==1?30:0),b:602720+index*1+mmkey.slice(-2,-1)*9991+(k==1?30:0), t:k==0?2:6,s:10},
                            {m:'sum',g:602701+index*1+mmkey.slice(-2,-1)*9991,l:[602711+index*1+mmkey.slice(-2,-1)*9991,602741+index*1+mmkey.slice(-2,-1)*9991]}
                        ]
                    }
                    nProp.list.push(sProp)
                })
                pInArr.push(nProp)
            })

            fArr=['医学隔离病例情况','发热情况','疑似病例','确诊病例','死亡病例']
            fArr.forEach((d,k)=> {
                let gArr = ['学生总数（以学籍口）', '教职工总数']
                gArr.forEach((sub, k1) => {
                    nProp = {list: []}
                    nProp.title = d + ' -' + sub
                    if (k != 2 && k != 3) nProp.tips = '需同时填报个体病例'
                    let list = k1 == 0 ? XDICLIST.labelListStd : XDICLIST.labelListTea
                    list.forEach((lb, ky) => {
                        let sProp = {label: lb}
                        sProp.prop = 602201 + index * 1 + mmkey.slice(-2, -1) * 9991 + k * 100 + ky * 10 + (k1 == 1 ? 40 : 0)
                        if (ky == 0) sProp.edit = false
                        else {
                            sProp.calcs = [
                                {
                                    m: 'sum',
                                    g: 602201 + index * 1 + mmkey.slice(-2, -1) * 9991 + k * 100 + (k1 == 1 ? 40 : 0),
                                    b: 602210 + index * 1 + mmkey.slice(-2, -1) * 9991 + k * 100 + (k1 == 1 ? 40 : 0),
                                    t: list.length - 1,
                                    s: 10
                                }
                            ]
                        }
                        nProp.list.push(sProp)
                    })
                    pInArr.push(nProp)
                })
            })
            index+=1
        }
        let rArray={}
        Object.keys(pArr).map(k=>{
            let v=pArr[k]
            for(let i in v){
                let list=v[i].list
                for(let l of list){
                    if(!l.comType)l.comType='int'
                    if(l.edit!==false)l.edit=edit
                }
            }
        })
        return pArr
    },
    601101(XDICLIST,edit){
        const schoolLX=XDICLIST.schoolType
        let pArr={},index=0
        const stdLen=XDICLIST.labelListStd.length,teaLen=XDICLIST.labelListTea.length
        for(let mmkey in schoolLX){
            pArr[mmkey]=[]
            let pInArr=pArr[mmkey],nProp={}
            nProp.title=null
            nProp.list=[]
            nProp.list.push({label:'学校数量',prop:index*1+mmkey.slice(-2,-1)*9991+501091})
            nProp.list.push({label:'在校生总数（事业年报数）',prop:index*1+mmkey.slice(-2,-1)*9991+601001})
            nProp.list.push({label:'教职工总数（事业年报数）',prop:index*1+mmkey.slice(-2,-1)*9991+601011})
            pInArr.push(nProp)

            let fArr=['在校学生情况（校内）','春节期间留校学生情况','返校学生情况（1月24日后起算）']
            fArr.forEach((d,k)=>{
                nProp={list:[]}
                nProp.title=d
                XDICLIST.labelListStd.forEach((lb,ky)=>{
                    let sProp={label:lb}
                    sProp.prop=601101+index*1+mmkey.slice(-2,-1)*9991+ky*10+(k==1?1000:k==2?1800:0)
                    if(ky==0)sProp.edit=false
                    else{
                        // if(ky==1)sProp.maxP=601101+index*1+mmkey.slice(-2,-1)*9991+ky*10+(k==1?1000:k==2?1800:0)
                        sProp.calcs=[
                            {m:'sum',g:601101+index*1+mmkey.slice(-2,-1)*9991+(k==1?1000:k==2?1800:0),b:601110+
                                    index*1+mmkey.slice(-2,-1)*9991+(k==1?1000:k==2?1800:0),t:stdLen,s:10},
                            {m:'sum',g:601101+index*1+ky*10+mmkey.slice(-2,-1)*9991,l:[601101+index*1+ky*10+mmkey.slice(-2,-1)*9991+1000,601101+index*1+ky*10+mmkey.slice(-2,-1)*9991+1800]},
                            {m:'sum',g:601101+index*1+mmkey.slice(-2,-1)*9991,l:[601101+index*1+mmkey.slice(-2,-1)*9991+1000,601101+index*1+mmkey.slice(-2,-1)*9991+1800]}
                        ]
                    }
                    if(k==0)sProp.edit=false
                    nProp.list.push(sProp)
                    if(k>0 && ky==1) {
                        XDICLIST.labelListStdWH.forEach((lb1, ky1) => {
                            let sProp = {label: lb1}
                            sProp.prop = 602141+index*1+mmkey.slice(-2,-1)*9991+ky1*10+800*(k-1)
                            sProp.maxP=602141+index*1+mmkey.slice(-2,-1)*9991+800*(k-1)+(ky1-1)*30
                            nProp.list.push(sProp)
                        })
                    }
                })
                pInArr.push(nProp)
            })

            nProp={list:[]}
            nProp.title='在校教职工情况（校内）'
            XDICLIST.labelListTea.forEach((lb,ky)=>{
                let sProp={label:lb}
                sProp.prop=601101+index*1+mmkey.slice(-2,-1)*9991+ky*10+40
                if(ky==0)sProp.edit=false
                else{
                    sProp.calcs=[
                        {m:'sum',g:601101+index*1+mmkey.slice(-2,-1)*9991+40,b:601110+ index*1+mmkey.slice(-2,-1)*9991+40,t:stdLen,s:10}
                    ]
                }
                nProp.list.push(sProp)
            })
            pInArr.push(nProp)

            pInArr.push({title:'正在实习学生情况',list:[{label:'合计',prop:index*1+mmkey.slice(-2,-1)*9991+602701,edit:false}]})

            fArr=['省内实习学生数','外省实习学生数']
            fArr.forEach((d,k)=>{
                nProp={list:[]}
                nProp.title='正在实习学生情况- '+d
                let list=k==0?['小计','成都','其他市州']:['小计','湖北','浙江','广东','河南','湖南','其他省市']
                list.forEach((lb,ky)=>{
                    let sProp={label:lb}
                    sProp.prop=602711+index*1+mmkey.slice(-2,-1)*9991+ky*10+(k==1?30:0)
                    if(ky==0)sProp.edit=false
                    else{
                        sProp.calcs=[
                            {m:'sum',g:602711+index*1+mmkey.slice(-2,-1)*9991+(k==1?30:0),b:602720+index*1+mmkey.slice(-2,-1)*9991+(k==1?30:0), t:k==0?2:6,s:10},
                            {m:'sum',g:602701+index*1+mmkey.slice(-2,-1)*9991,l:[602711+index*1+mmkey.slice(-2,-1)*9991,602741+index*1+mmkey.slice(-2,-1)*9991]}
                        ]
                    }
                    nProp.list.push(sProp)
                })
                pInArr.push(nProp)
            })

            fArr=['医学隔离病例情况','发热情况','疑似病例','确诊病例','死亡病例']
            fArr.forEach((d,k)=> {
                let gArr = ['学生总数（以学籍口）', '教职工总数']
                gArr.forEach((sub, k1) => {
                    nProp = {list: []}
                    nProp.title =  d + '-' + sub
                    if (k != 2 && k != 3) nProp.tips = '需同时填报个体病例'
                    let list = k1 == 0 ? XDICLIST.labelListStd : XDICLIST.labelListTea
                    list.forEach((lb, ky) => {
                        let sProp = {label: lb}
                        sProp.prop = 602201 + index * 1 + mmkey.slice(-2, -1) * 9991 + k * 100 + ky * 10 + (k1 == 1 ? 40 : 0)
                        if (ky == 0) sProp.edit = false
                        else {
                            sProp.calcs = [
                                {
                                    m: 'sum',
                                    g: 602201 + index * 1 + mmkey.slice(-2, -1) * 9991 + k * 100 + (k1 == 1 ? 40 : 0),
                                    b: 602210 + index * 1 + mmkey.slice(-2, -1) * 9991 + k * 100 + (k1 == 1 ? 40 : 0),
                                    t: list.length - 1,
                                    s: 10
                                }
                            ]
                        }
                        nProp.list.push(sProp)
                    })
                    pInArr.push(nProp)
                })
            })
            index+=1
        }
        let rArray={}
        Object.keys(pArr).map(k=>{
            let v=pArr[k]
            for(let i in v){
                let list=v[i].list
                for(let l of list){
                    if(!l.comType)l.comType='int'
                    if(l.edit!==false)l.edit=edit
                }
            }
        })
        return pArr
    }
}