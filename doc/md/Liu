# 库存管理人员软件规格说明及测试用例套件


标签（空格分隔）： 

---

## 2.1.2 业务需求
BR1 : 系统使用后，库存积压、缺货和报废的现象要减少50%。

## 2.2 系统功能
SF1 ： 帮助库存管理人员管理商品
SF2 ： 帮助库存管理人员管理库存，定期查看、盘点库存

## 2.3 用户特征

| 用户 | 特征  |
| ---- |   ----  |
| <br>库存管理人员| 有1到2名库存管理人员。他们每天的任务就是对商品分类和商品的信息的管理，同时定期查看、盘点库存，盘点当天的各种商品的名称，型号，库存数量，库存均价，批次，批号，出厂日期，查看某个时间段内的出/入库数量/金额，销售/进货的数量/金额以及合计库存数量。他们每天还要制定各种单据，会根据总经理制定的促销赠送策略制定库存赠送单，通过比较实际库存和系统库存中的数量，生成相应的库存赠送单、库存报损单、库存报溢单、库存报警单，并送总经理审批。他们对基本持积极态度，但不希望增加现有工作量，能够简单使用办公信息化系统，操作计算机的技能一般，希望新系统尽可能帮他们解决工作上的问题 |

## 2.4 约束
CON1： 系统将运行在Window 10 操作系统上
CON2： 系统使用图形界面
CON3： 项目要使用持续集成方法进行开发
CON4： 在开发中，开发者要提交软件需求规格说明文档、设计描述文档和测试报告。

## 2.5 假设和依赖
AE1：每天生成的库存报溢单、库存报损单、库存报警单上的商品种类数不能超过总数量5% 


# 3.详细需求描述

## 3.1 对外接口需求

### 3.1.1 用户界面

### 3.1.2 通信接口

##3.2 功能需求

### 3.2.1 商品分类管理
### 3.2.1.1 特性描述
在库存管理人员需要管理商品分类时，一位经过验证的库存管理人员可以进入商品分类界面，商品分类呈树状结构。
优先级 = 低

### 3.2.1.2 刺激/响应序列
刺激：库存管理人员点击商品分类模块
响应：系统显示商品分类界面
刺激：库存管理人员点击增加，输入新分类信息
响应：系统生成新分类并自动生成其分类编号
刺激：库存管理人员选择分类，点击删除
响应：系统删除所选分类
刺激：库存管理人员选择分类，进行修改
响应：系统显示所选分类的所有信息
刺激：库存管理人员修改信息
响应：系统更新分类信息

### 3.2.1.3 相关功能需求
|GoodCategoryManagement |  商品分类管理 |
|---|---|
|GoodCategoryManagement.View <br> GoodCategoryManagement.View.Cancle <br>| 在库存管理人员点击分类节点时，系统要显示其子分类 <br> 在库存管理人员请求隐藏子分类时，再次点击分类节点，系统要隐藏其子分类|
|GoodCategoryManagement.Input.NewCategory <br>GoodCategoryManagement.Input.Categorynewinformation <br> GoodCategoryManagement.Input.Invalid| 在库存管理人员输入新分类信息时，系统要进行相应更改，参见GoodCategoryManagement.Category <br> 在库存管理人员输入、修改分类新信息时，系统要进行相应更改，参见GoodCategoryManagement.Category <br> 在库存管理人员输入时，如果格式不合法，系统提示输入无效|
|GoodCategoryManagement.Category.Add <br>GoodCategoryManagement.Delete <br>GoodCategoryManagement.Modify <br>| 在库存管理人员请求增加新商品分类时，系统要显示新增分类信息表，自动生成其编号 <br> 在库存人员删除分类时，系统要进行相应更改 <br> 在库存管理人员请求修改商品分类信息时，系统要显示该商品分类的所有信息|
|GoodCategoryManagement.End.Null| 在库存管理人员修改新增分类时，什么也不输入就退出时，系统要关闭新增修改商品分类任务|
|GoodCategoryManagement.Update| 在库存管理人员管理分类完成之后，系统要更新分类界面|
|GoodCategoryManagement.Close|系统关闭商品分类管理任务|

### 3.2.2 商品管理
### 3.2.2.1 特性描述
在库存管理人员需要管理商品时，一位经过验证的库存管理人员可以直接进入商品界面或者点击商品分类页叶节点进入。
优先级 = 低

### 3.2.2.2 刺激/响应序列
刺激：库存管理人员点击商品模块
响应：系统显示商品界面
刺激：库存管理人员点击增加，输入新商品信息
响应：系统生成新商品并自动生成其商品编号
刺激：库存管理人员选择商品，点击删除
响应：系统删除所选商品
刺激：库存管理人员选择商品，进行修改
响应：系统显示所选商品的所有信息
刺激：库存管理人员修改信息
响应：系统更新分类信息
刺激：库存管理人员选择查询商品
响应：库存管理人员显示输入框
刺激：库存管理人员输入商品编号或关键字
响应：系统显示商品及其信息

### 3.2.2.3 相关功能需求
|GoodManagement |  商品管理 |
|---|---|
|GoodManagement.Input.NewGoods <br> <br>GoodCategoryManagement.Input.CategoryNewInformation <br><br> GoodManagement.Input.Check<br><br>GoodManagement.Input.Invalid | 在库存管理人员输入新商品信息时，系统要进行相应更改，参见GoodManagement.Good <br> 在库存管理人员输入、修改分类商品时，系统要进行相应更改，参见GoodCategoryManagement.Good <br> 在库存管理人员输入商品编号或其关机子时，系统要进行检索 <br> 在库存管理人员输入时，如果格式不合法，系统提示输入无效 |
|GoodManagement.Good.Add <br><br>GoodManagement.Good.Delete <br>GoodManagement.Good.Modify <br><br> GoodManagement.Good.enquiry| 在库存管理人员请求增加新商品时，系统要显示新增商品信息表，自动生成其编号 <br> 在库存人员删除商品时，系统要进行相应更改 <br> 在库存管理人员请求修改商品信息时，系统要显示该商品分类的所有信息 <br> 在库吨管理人员请求查询商品时，系统要显示该商品的所有信息或显示该商品不存在|
|GoodManagement.End.Null| 在库存管理人员修改新增分类时，什么也不操作就退出时，系统关闭商品管理任务|
|GoodManagement.Update| 在库存管理人员管理商品完成之后，系统要更新商品界面|
|GoodCategoryManagement.Close|系统关闭商品分类管理任务|

### 3.2.3 库存查看
### 3.2.3.1 特性描述
当想了解某个时间段库存情况时，一位经过验证的库存管理人员可以进行库存查看
优先级 = 低
### 3.2.3.2 刺激/响应序列
刺激：库存管理人员点击库存查看模块
响应：系统提示设定一个时间段
刺激：库存管理人员设定时间段
响应：系统生统计此时间段内的出/入库数量/金额，销售/进货的数量/金额与库存合计数量

### 3.2.3.3 相关功能需求
|StorageCheck |  库存查看 |
|---|---|
|StorageCheck.Input | 在库存管理人员设置时间段时，系统要统计此时间段内的出/入库数量/金额，销售/进货的数量/金额与库存合计数量 |
|StorageCheck.View| 系统显示此时间段内的出/入库数量/金额，销售/进货的数量/金额与库存合计数量|
|StorageCheck.Close|在库存管理人员请求结束库存查看任务时，系统要关闭库存查看任务|

### 3.2.4 库存盘点
### 3.2.4.1 特性描述
当想统计当天库存中商品的情况时，一位经过验证的库存管理人员可以进行库存盘点
优先级 = 低
### 3.2.4.2 刺激/响应序列
刺激：库存管理人员点击库存盘点模块
响应：系统生成当天的库存快照
刺激：库存管理人员选择导出excel
响应：系统导出库存快照excel格式

### 3.2.4.3 相关功能需求
|StorageStorktaking |  库存盘点 |
|---|---|
|<br>StorageStorktaking.View| 系统生成当天的库存快照，包括当天的各种商品的名称，型号，库存数量，库存均价，批次，批号，出厂日期|
|StorageStorktaking.Excel|系统导出当天库存快照的excel格式|
|StorageStorktaking.Close|在库存管理人员请求结束库存盘点任务时，系统要关闭库存盘点任务|

### 3.2.5 库存赠送单
### 3.2.5.1 特性描述
在库存管理人员响应总经理的赠送策略时，系统库存管理人员允许生成库存赠送单
优先级 = 低
### 3.2.5.2 刺激/响应序列
刺激：库存管理人员点击库存赠送单模块
响应：系统显示商品界面
刺激：库存管理人员选择商品
响应：系统提示输入赠送数量
刺激：库存管理人员输入赠送数量
响应：系统生成库存赠送单并提交审批

### 3.2.5.3 相关功能需求
|StorageSendList |  库存赠送单 |
|---|---|
|StorageSendList.View <br><br> StorageSendList.View.SendList |在库存管理人员请求生成库存赠送单的时候，系统显示仍有库存的商品<br> 系统生成并显示库存赠送单|
|StorageSendList.Good.Choose <br><br>StorageSendList.Good.Numbers<br><br> StorageSendList.Good.enquiry<br><br>StorageSendList.Good.Invalid <br><br>StorageSendList.Good.Cancle| 在库存管理人员选择商品时，系统要标识选中的商品并提示输入数量<br> 系统应该允许库存管理人员输入赠送商品的数量<br>系统应该允许库存管理人员输入商品编号、关键字查询商品来选择<br>当输入的数量少于库存数量或者格式不合法，系统提示数量不足或输入无效<br>系统允许库存管理人员取消所选中的商品|
|StorageSendList.Review|系统将生成的库存赠送单提交审批 |
|StorageSendList.Update|当库存赠送单获得审批时，系统会更新库存|
|StorageSendList.close|当库存管理人员请求结束库存赠送单任务时，系统结束库存赠送单任务|

### 3.2.6 库存报溢单
### 3.2.6.1 特性描述
在发现库存中商品数量溢出时，系统库存管理人员允许生成库存报溢单
优先级 = 中
### 3.2.6.2 刺激/响应序列
刺激：库存管理人员点击库存报溢单模块
响应：系统显示商品界面
刺激：库存管理人员选择商品
响应：系统提示输入实际库存数量
刺激：库存管理人员输入实际库存数量
响应：系统根据实际数量和系统数量的比较生成库存报溢单并提交审批

### 3.2.6.3 相关功能需求
|StorageSendList |  库存报溢单 |
|---|---|
|StorageOverFlowList.View <br><br> StorageOverFlowList.View.OverFlowList |在库存管理人员请求生成库存报溢单的时候，系统显示库存中的商品<br> 系统生成并显示库存报溢单|
|StorageOverFlowList.Good.Choose <br><br>StorageOverFlowList.Good.Numbers<br><br> StorageOverFlowList.Good.enquiry<br><br>StorageOverFlowList.Good.Invalid <br><br>StorageOverFlowList.Good.Cancle| 在库存管理人员选择商品时，系统要标识选中的商品并提示输入实际库存数量<br> 系统应该允许库存管理人员输入商品的实际库存数量数量<br>系统应该允许库存管理人员输入商品编号、关键字查询商品来选择<br>当输入的数量异常或者格式不合法，系统提示数量不足或输入无效<br>系统允许库存管理人员取消所选中的商品|
|StorageOverFlowList.Compare|系统将比较实际库存数量和系统数量来决定是否生成库存报溢单|
|StorageOverFlowList.Faliure|系统提示无法生成库存报溢单|
|StorageOverFlowList.Review|系统将生成的库存报溢单提交审批 |
|StorageOverFlowList.Update|当库存报溢单获得审批时，系统会更新库存|
|StorageOverFlowList.close|当库存管理人员请求结束库存报溢单任务时，系统结束库存报溢单任务|

### 3.2.7 库存报损单
### 3.2.7.1 特性描述
在发现残次品、商品报废时，系统库存管理人员允许生成库存报损单
优先级 = 中
### 3.2.7.2 刺激/响应序列
刺激：库存管理人员点击库存报损单模块
响应：系统显示商品界面
刺激：库存管理人员选择商品
响应：系统提示输入实际库存数量
刺激：库存管理人员输入实际库存数量
响应：系统根据实际数量和系统数量的比较生成库存报损单并提交审批

### 3.2.7.3 相关功能需求
|StorageDamageList |  库存报损单 |
|---|---|
|StorageDamageList.View <br><br> StorageDamageList.View.StorageDamageList |在库存管理人员请求生成库存报损单的时候，系统显示库存中的商品<br> 系统生成并显示库存报损单|
|StorageDamageList.Good.Choose <br><br>StorageDamageList.Good.Numbers<br><br> StorageDamageList.Good.enquiry<br><br>StorageDamageList.Good.Invalid <br><br>StorageDamageList.Good.Cancle| 在库存管理人员选择商品时，系统要标识选中的商品并提示输入实际库存数量<br> 系统应该允许库存管理人员输入商品的实际库存数量数量<br>系统应该允许库存管理人员输入商品编号、关键字查询商品来选择<br>当输入的数量异常或者格式不合法，系统提示数量不足或输入无效<br>系统允许库存管理人员取消所选中的商品|
|StorageDamageList.Compare|系统将比较实际库存数量和系统数量来决定是否生成库存报损单|
|StorageDamageList.Faliure|系统提示无法生成库存报损单|
|StorageDamageList.Review|系统将生成的库存报损单提交审批 |
|StorageDamageList.Update|当库存报损单获得审批时，系统会更新库存|
|StorageDamageList.close|当库存管理人员请求结束库存报损单任务时，系统结束库存报损单任务|

### 3.2.8 库存报警单
### 3.2.7.1 特性描述
在发现库存数量不足时，系统库存管理人员允许生成库存报损单
优先级 = 中
### 3.2.8.2 刺激/响应序列
刺激：库存管理人员点击库存报警单单模块
响应：系统显示商品界面
刺激：库存管理人员选择商品
响应：系统提示输入实际库存数量
刺激：库存管理人员输入实际库存数量
响应：系统根据实际数量和警戒数量的比较生成库存报警单提交审批

### 3.2.8.3 相关功能需求
|StorageWarningList |  库存报警单 |
|---|---|
|StorageWarningList.View <br><br> StorageWarningList.View.StorageWarningList |在库存管理人员请求生成库存报警单的时候，系统显示库存中的商品<br> 系统生成并显示库存报警单|
|StorageWarningList.Good.Choose <br><br>StorageWarningList.Good.Numbers<br><br> StorageWarningList.Good.enquiry<br><br>StorageWarningList.Good.Invalid <br><br>StorageWarningList.Good.Cancle<br>StorageWarningList.Good.Modify| 在库存管理人员选择商品时，系统要标识选中的商品并提示输入实际库存数量<br> 系统应该允许库存管理人员输入商品的实际库存数量<br>系统应该允许库存管理人员输入商品编号、关键字查询商品来选择<br>当输入的数量异常或者格式不合法，系统提示数量不足或输入无效<br>系统允许库存管理人员取消所选中的商品<br>系统应该允许库存管理人员修改商品的警戒数量|
|StorageWarningList.Compare|系统将比较实际库存数量和系统数量来决定是否生成库存报警单|
|StorageWarningList.Faliure|系统提示无法生成库存报警单|
|StorageWarningList.Review|系统将生成的库存报警单提交审批 |
|StorageWarningList.close|当库存管理人员请求结束库存报警单任务时，系统结束库存报警单任务|

## 3.3 非功能需求
### 3.3.1 安全性
Safety1 ： 系统应该只允许经过验证和授权的用户访问
Safety2 ： 每一个用户只能访问自己权限内的工作，不能访问其他用户的工作
Safety3 ： 系统中有一个默认的管理员账户，该账户只允许管理员用户修改口令

### 3.3.2 可维护性
Modification1:如果系统日安家新的分类和新的商品，系统要在3人一天内完成

### 3.3.3 易用性
Usability1 ： 使用系统一个月的库存管理人员内在三十分钟之内盘点完库存

### 3.3.4 可靠性
Reliability6 ： 在客户端与服务器通信时，如果网络故障，系统不能出现故障
    Reliability6.1 : 客户端应该检测到故障，并尝试连接网络3次，每次15秒
    Reliability6.1.1: 重新连接后，客户端应该继续之前的工作
    Reliability6.1.2 ： 如果重新连接不成功，客户端应该等待五分钟后再次尝试连接
    Reliability6.1.2.1: 重新连接后，客户端应该继续之前的工作
    Reliability6.1.2.2: 如果重新连接仍然不成功，客户端报警

### 3.3.5 业务规则

### 3.3.6 约束
IC1 ： 系统要在网络上分布为一个服务器和多个客户端

## 3.4 数据需求
### 3.4.2
Default1 ： 商品的数量默认为1
Default2 ： 时间默认为当天

### 3.4.3 数据格式要求
Format1 ： 字符输入不能超过24个字符
Format2 ： 价格和费用的格式必须是大于等于0，精确到小数点后两位的浮点数
Format3 ： 计算时采用LONG LONG的数据格式


# 测试用例套件

测试用例套件对**商品分类管理**需求的覆盖情况

|编号 |  测试用例套件1 | 测试用例套件2|测试用例套件3|
|---|---| --- |------|
|GoodCategoryManagement.View | TUS1 |  | |
|GoodCategoryManagement.View.Cancle  | TUS1 | |   |
|GoodCategoryManagement.Input.NewCategory| |TUS2||
|GoodCategoryManagement.Input.CategoryNewInformation ||TUS2||
|GoodCategoryManagement.Input.Invalid| | TUS2|TUS3 |
|GoodCategoryManagement.Category.Add || | TUS3|
|GoodCategoryManagement.Delete || | TUS3|
|GoodCategoryManagement.Modify | | | TUS3|
|GoodCategoryManagement.End.Null| TUS1| | |
|GoodCategoryManagement.Update|  TUS1 |||
|GoodCategoryManagement.Close|TUS1|||

|测试用例套件|覆盖流程|
|---|---|

|TUS1|正常流程|1|||
|---|---|
|TUS2|正常流程||2||||
|TUS3|正常流程|||3||

### TUS1的测试用例
|编号|测试条件|预期输出|
|---|----|
|TUS1-1|无|系统不做任何处理，关闭管理商品分类任务|
|TUS1-2|完成操作后库存管理人员查看商品分类|系统更新商品分类界面|
|TUS1-3|库存管理人员退出商品分类界面|系统关闭管理商品分类任务|
|TUS1-4|库存管理人员直接退出商品分类模块|系统关闭商品分类任务|

### TUS2的测试用例
|编号|测试条件|预期输出|
|---|----|
|TUS2-1|输入新分类信息|系统更新商品分类界面|
|TUS2-2|输入分类新信息|系统将修改分类信息并更新界面|
|TUS2-3|输入不合法的数字格式|系统提示输入无效|

### TUS3的测试用例
|编号|测试条件|预期输出|
|---|----|
|TUS3-1|输入不合法的分类信息|系统提示输入无效|
|TUS3-2|添加新分类|系统在指点的节点下更新商品分类界面|
|TUS3-3|删除分类|系统删除在指定节点下分类，并更新商品分类洁面|
|TUS3-4|修改分类|系统修改分类信息并更新界面|

测试用例套件对**商品管理**需求的覆盖情况

|编号 |  测试用例套件4 | 测试用例套件5|测试用例套件6|
|---|---|
|GoodManagement.Input.NewGoods ||TUS5| |
|GoodCategoryManagement.Input.CategoryNewInformation||TUS5||
|GoodManagement.Input.Check||TUS5||
|GoodManagement.Input.Invalid| |TUS5||
|GoodManagement.Good.Add |||TU6|
|GoodManagement.Good.Delete |||TUS6|
|GoodManagement.Good.Modify |||TUS6|
|GoodManagement.Good.enquiry| |TUS5||
|GoodManagement.End.Null| TUS4 |
|GoodManagement.Update| TUS4|
|GoodCategoryManagement.Close|TUS4|

|测试用例套件|覆盖流程|
|---|---|

|TUS4|正常流程|||3|
|---|---|
|TUS5|正常流程|1|||||
|TUS6|正常流程||2|||

### TUS4测试用例
|编号|测试条件|预期输出|
|---|----|
|TUS4-1|无|系统不做任何处理，关闭管理商品任务|
|TUS4-2|库存管理人员退出商品界面|系统关闭管理商品任务|
|TUS4-3|库存管理人员直接退出商品模块|系统关闭商品任务|

### TUS5的测试用例
|编号|测试条件|预期输出|
|---|----|
|TUS5-1|输入新商品信息|系统更新商品界面|
|TUS5-2|输入商品新信息|系统将修改商品信息并更新界面|
|TUS5-3|输入不合法的数字格式|系统提示输入无效|
|TUS5-4|(输入关键字或商品编号)|系统检索并显示该商品|
### TUS6的测试用例
|编号|测试条件|预期输出|
|---|----|
|TUS6-1|添加新商品|系统在指点的节点下更新商品界面|
|TUS6-2|删除商品|系统删除在指定节点下商品，并更新商品分类洁面|
|TUS6-3|修改商品信息|系统修改商品信息并更新界面|


测试用例套件对**库存查看**需求的覆盖情况

|编号 |  测试用例套件7 |
|---|---|
|StorageCheck.Input | TUS7 |
|StorageCheck.View| |
|StorageCheck.Close|TUS7|

|测试用例套件|覆盖流程|
|---|---|

|TUS7|正常流程|2|3|
|---|---|
### TUS7的测试用例
|编号|测试条件|预期输出|
|---|----|
|TUS7-1|输入时间段|系统要统计此时间段内的出/入库数量/金额，销售/进货的数量/金额与库存合计数量|
|TUS7-2|无|系统关闭查看库存任务|


系统生成当天的库存快照，包括当天的各种商品的名称，型号，库存数量，库存均价，批次，批号，出厂日期|
|StorageStorktaking.Excel|系统导出当天库存快照的excel格式|
|StorageStorktaking.Close|在库存管理人员请求结束库存盘点任务时，系统要关闭库存盘点任务|

|测试用例套件|覆盖流程|
|---|---|

|TUS7|正常流程|2|3|
|---|---|

测试用例套件对**库存赠送单**需求的覆盖情况
|编号 |  测试用例套件8 |测试用例套件9|
|---|---|
|StorageSendList.View| | | |
|StorageSendList.Good.Choose| | | | 
|StorageSendList.Good.Numbers| TUS8| | |
|StorageSendList.Good.enquiry| TUS8| | |
|StorageSendList.Good.Invalid| TUS8| | |
|StorageSendList.Review||||
|StorageSendList.Update||TUS8|
|StorageSendList.close||TUS9|

### TUS8的测试用例
|编号|输入|预期输出|
|---|----|
|TUS8-1|商品1（数量50）<br>商品2（30）|系统生成库存赠送单，显示有50个商品1、30个商品2，商品数量共80个|
|TUS8-2|商品1（数量33）<br>商品2（35）|系统生成库存赠送单，显示有33个商品1、35个商品2，商品数量共68个|
|TUS8-3|商品1（数量-50）<br>商品2（30）|系统提示输入无效，无法生成库存赠送单|

### TUS9的测试用例
|编号|输入|预期输出|
|---|----|
|TUS9-1|无|系统关闭库存赠送单任务|

|测试用例套件|覆盖流程|
|---|---|

|TUS8|正常流程|2||
|---|---|
|TUS9|正常流程||4||


测试用例套件对**库存报溢单**需求的覆盖情况
|编号 |  测试用例套件10 |测试用例套件11|
|---|---|
|StorageOverFlowList.View|||
|StorageOverFlowList.Good.Choose |||
|StorageOverFlowList.Good.Numbers|TUS10||
|StorageOverFlowList.Good.enquiry||TUS11|
|StorageOverFlowList.Good.Invalid|TUS10||
|StorageOverFlowList.Compare|TUS10||
|StorageOverFlowList.Faliure|TUS10||
|StorageOverFlowList.Review|||
|StorageOverFlowList.Update||TUS11|
|StorageOverFlowList.close||TUS11|

|测试用例套件|覆盖流程|
|---|---|

|TUS10|正常流程|2||1（扩展）|
|---|---|
|TUS11|正常流程||4||

### TUS10的测试用例
|编号|输入|预期输出|
|---|----|
|TUS10-1|商品1（实际库存55，系统库存50）<br>商品2（实际库存35，系统库存30）|系统生成库存报溢单，显示5个商品1报溢，5个商品2报溢 |
|TUS10-2|商品1（实际库存45，系统库存40）<br>商品2（实际库存45，系统库存45）|系统生成库存报溢单，显示5个商品1报溢，并提示商品2报溢失败 |
|TUS10-3|商品1（实际库存45，系统库存45）<br>商品2（实际库存35，系统库存33）|系统生成库存报溢单，显示商品1、商品2报溢失败 |
|TUS10-4|商品1（实际库存-5，系统库存45）|系统提示输入负数 |

### TUS11的测试用例
|编号|输入|预期输出|
|---|----|
|TUS11-1|商品编号或关键字|系统检索并显示该商品|
|TUS11-2|商品编号或关键字|系统检索并显示无该商品|
|TUS11-3|无|系统关闭库存报溢单任务|

测试用例套件对**库存报损单**需求的覆盖情况
|编号 |  测试用例套件12 |测试用例套件13|
|---|---|
|StorageDanmageList.View|||
|StorageDamageList.Good.Choose |||
|StorageDamageList.Good.Numbers|TUS12||
|StorageDamageList.Good.enquiry||TUS13|
|StorageDamageList.Good.Invalid|TUS12||
|StorageDamageList.Compare|TUS12||
|StorageDamageList.Faliure|TUS12||
|StorageDamgeList.Review|||
|StorageDamgeList.Update|||
|StorageDamgeList.close||TUS13|

|测试用例套件|覆盖流程|
|---|---|

|TUS12|正常流程|2||1(扩展)|
|---|---|
|TUS13|正常流程||4||||

### TUS12的测试用例
|编号|输入|预期输出|
|---|----|
|TUS12-1|商品1（实际库存50，系统库存55）<br>商品2（实际库存30，系统库存35）|系统生成库存报损单，显示5个商品1报损,5个商品2报损 |
|TUS12-2|商品1（实际库存40，系统库存45）<br>商品2（实际库存45，系统库存45）|系统生成库存报损单，显示5个商品1报损，并提示商品2报损失败 |
|TUS12-3|商品1（实际库存45，系统库存45）<br>商品2（实际库存35，系统库存33）|系统生成库存报损单，显示商品1、商品2报溢报损 |
|TUS12-4|商品1（实际库存-5，系统库存45）|系统提示输入负数 |

### TUS13的测试用例
|编号|输入|预期输出|
|---|----|
|TUS13-1|商品编号或关键字|系统检索并显示该商品|
|TUS13-2|商品编号或关键字|系统检索并显示无该商品|
|TUS11-3|无|系统关闭库存报损单任务|

测试用例套件对**库存报警单**需求的覆盖情况

|编号 |  测试用例套件14 |测试用例套件15|
|---|---|
|StorageWarningList.View |||
|StorageWarningList.View.StorageWarningList|||
|StorageWarningList.Good.Choose |||
|StorageWarningList.Good.Numbers|TUS14||
|StorageWarningList.Good.enquiry||TUS15|
|StorageWarningList.Compare|TUS14||
|StorageWarningList.Faliure|TUS14||
|StorageWarningList.Review|||
|StorageWarningList.close||TUS15

|测试用例套件|覆盖流程|
|---|---|

|TUS114|正常流程|2||1（扩展）|
|---|---|
|TUS15|正常流程||4||||

### TUS14的测试用例
|编号|输入|预期输出|
|---|----|
|TUS14-1|设置警戒数量|系统提示修改成功 |
|TUS14-2|商品1（库存数量45，警戒数量50）<br>商品2（库存数量25，警戒数量30）|系统生成库存报警单，显示低于商品1警戒数量5个,商品2低于警戒数量5个|
|TUS14-3|商品1（库存数量55，警戒数量50）<br>商品2（库存数量25，警戒数量30）|系统生成库存报警单，商品2低于警戒数量5个。并提示商品1库存数量不低于警戒数量|
|TUS14-4|商品1（库存数量55，警戒数量50）<br>商品2（库存数量35，警戒数量30）|系统提示商品1、商品2库存数量不低于警戒数量|
|TUS14-5|商品1（库存数量55，警戒数量-50）|系统提示警戒数量为负|

### TUS15的测试用例
|编号|输入|预期输出|
|---|----|
|TUS15-1|商品编号或关键字|系统检索并显示该商品|
|TUS15-2|商品编号或关键字|系统检索并显示无该商品|
|TUS15-3|无|系统关闭库存报损单任务|
