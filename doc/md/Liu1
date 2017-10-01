# 库存管理人员，度量需求

标签（空格分隔）： 未分类
F = 41
1.06
---
## 商品分类管理
输入:3
输出:6
查询:5
逻辑文件:3
对外接口:0
FP = （3+6+5+3）*1.06 = 18.02 

### 3.2.1.3 相关功能需求
|GoodCategoryManagement |  商品分类管理 |
|---|---|
|GoodCategoryManagement.View <br> GoodCategoryManagement.View.Cancle <br>| 在库存管理人员点击分类节点（查询）时，系统要显示其子分类（输出） <br> 在库存管理人员请求隐藏子分类时，再次点击分类节点(查询)，系统要隐藏其子分类（输出）|
|GoodCategoryManagement.Input.NewCategory <br>GoodCategoryManagement.Input.Categorynewinformation <br> GoodCategoryManagement.Input.Invalid| 在库存管理人员输入新分类信息时（输入），系统要进行相应更改（逻辑文件），参见GoodCategoryManagement.Category <br> 在库存管理人员输入、修改分类新信息（输入）时，系统要进行相应更改（逻辑文件），参见GoodCategoryManagement.Category <br> 在库存管理人员输入（输入）时，如果字符过多，系统提示输入无效（输出）|
|GoodCategoryManagement.Category.Add <br>GoodCategoryManagement.Delete <br>GoodCategoryManagement.Modify <br>| 在库存管理人员请求增加新商品分类（查询）时，系统要显示新增分类信息表（输出），自动生成其编号（逻辑文件） <br> 在库存人员删除分类（查询）时，系统要进行相应更改（逻辑文件） <br> 在库存管理人员请求修改商品分类信息时（查询），系统要显示该商品分类的所有信息（输出）|
|GoodCategoryManagement.End.Null| 在库存管理人员修改新增分类时，什么也不输入就退出时，系统要关闭新增修改商品分类任务|
|GoodCategoryManagement.Update| 在库存管理人员管理分类完成之后，系统要更新分类界面（输出）|
|GoodCategoryManagement.Close|系统关闭商品分类管理任务|

### 3.2.2 商品管理
输入:3
输出:5
查询:6
逻辑文件:3
对外接口:0
FP = （3+6+5+3）*1.06 = 18.02

### 3.2.2.3 相关功能需求
|GoodManagement |  商品管理 |
|---|---|
|GoodManagement.Input.NewGoods <br> <br>GoodCategoryManagement.Input.CategoryNewInformation <br><br> GoodManagement.Input.Check<br><br>GoodManagement.Input.Invalid | 在库存管理人员输入新商品信息时（输入），系统要进行相应更改（逻辑文件），参见GoodManagement.Good <br> 在库存管理人员输入（输入）、修改分类商品时（查询），系统要进行相应更改（逻辑文件），参见GoodCategoryManagement.Good <br> 在库存管理人员输入商品编号或其关键字时（输入），系统要进行检索 <br> 在库存管理人员输入时，如果字符过多，系统提示输入无效（输出） |
|GoodManagement.Good.Add <br><br>GoodManagement.Good.Delete <br>GoodManagement.Good.Modify <br><br> GoodManagement.Good.enquiry| 在库存管理人员请求增加新商品时（查询），系统要显示新增商品信息表（输出），自动生成其编号（逻辑文件）<br> 在库存人员删除商品时（查询），系统要进行相应更改（逻辑文件）<br> 在库存管理人员请求修改商品信息时（查询），系统要显示该商品分类的所有信息（输出） <br> 在库吨管理人员请求查询商品时（查询），系统要显示该商品的所有信息或显示该商品不存在（输出）|
|GoodManagement.End.Null| 在库存管理人员修改新增分类时（查询），什么也不操作就退出时，系统关闭商品管理任务|
|GoodManagement.Update| 在库存管理人员管理商品完成之后，系统要更新商品界面（输出）|
|GoodCategoryManagement.Close|系统关闭商品分类管理任务|

### 3.2.3 库存查看
输入:1
输出:2
查询:0
逻辑文件:0
对外接口:0
FP = (1+2)*3 = 1.06

### 3.2.3.3 相关功能需求
|StorageCheck |  库存查看 |
|---|---|
|StorageCheck.Input | 在库存管理人员设置时间段时（输入），系统要统计此时间段内的出/入库数量/金额，销售/进货的数量/金额与库存合计数量（输出） |
|StorageCheck.View| 系统显示此时间段内的出/入库数量/金额，销售/进货的数量/金额与库存合计数量（输出）|
|StorageCheck.Close|在库存管理人员请求结束库存查看任务时，系统要关闭库存查看任务|

### 3.2.4 库存盘点
输入:0
输出:2
查询:0
逻辑文件:0
对外接口:0
FP = 2 * 1.06 = 2.12

### 3.2.4.3 相关功能需求
|StorageStorktaking |  库存盘点 |
|---|---|
|<br>StorageStorktaking.View| 系统生成当天的库存快照，包括当天的各种商品的名称，型号，库存数量，库存均价，批次，批号，出厂日期（输出）|
|StorageStorktaking.Excel|系统导出当天库存快照的excel格式（输出）|
|StorageStorktaking.Close|在库存管理人员请求结束库存盘点任务时，系统要关闭库存盘点任务|

### 3.2.5 库存赠送单
输入:2
输出:2
查询:2
逻辑文件:2
对外接口:0
FP = (2+2+2+2)*1.06 = 8.48

### 3.2.5.3 相关功能需求
|StorageSendList |  库存赠送单 |
|---|---|
|StorageSendList.View <br><br> StorageSendList.View.SendList |在库存管理人员请求生成库存赠送单的时候(查询)，系统显示仍有库存的商品<br> 系统生成并显示库存赠送单（输出）|
|StorageSendList.Good.Choose <br><br>StorageSendList.Good.Numbers<br><br> StorageSendList.Good.enquiry<br><br>StorageSendList.Good.Invalid <br><br>StorageSendList.Good.Cancle| 在库存管理人员选择商品时（查询），系统要标识选中的商品并提示输入数量（输出）<br> 系统应该允许库存管理人员输入赠送商品的数量（输入）<br>系统应该允许库存管理人员输入商品编号、关键字（输入）查询商品来选择（逻辑文件）<br>当输入的数量少于库存数量或者输入负数，系统提示数量不足或输入无效（输出）<br>系统允许库存管理人员取消所选中的商品（查询）|
|StorageSendList.Review|系统将生成的库存赠送单提交审批 |
|StorageSendList.Update|当库存赠送单获得审批时，系统会更新库存（逻辑文件）|
|StorageSendList.close|当库存管理人员请求结束库存赠送单任务时，系统结束库存赠送单任务|

### 3.2.6 库存报溢单
输入:2
输出:4
查询:3
逻辑文件:3
对外接口:0
FP = (2+4+3+3)*1.06= 12.72

### 3.2.6.3 相关功能需求
|StorageSendList |  库存报溢单 |
|---|---|
|StorageOverFlowList.View <br><br> StorageOverFlowList.View.OverFlowList |在库存管理人员请求生成库存报溢单的时候（查询），系统显示库存中的商品（输出）<br> 系统生成并显示库存报溢单（输出）|
|StorageOverFlowList.Good.Choose <br><br>StorageOverFlowList.Good.Numbers<br><br> StorageOverFlowList.Good.enquiry<br><br>StorageOverFlowList.Good.Invalid <br><br>StorageOverFlowList.Good.Cancle| 在库存管理人员选择商品时（查询），系统要标识选中的商品并提示输入实际库存数量（输出）<br> 系统应该允许库存管理人员输入商品的实际库存数量数量（输入）<br>系统应该允许库存管理人员输入商品编号、关键字查询商品来选择（输入）<br>当输入的数量少于系统数量或者负数，系统提示数量不足或输入负数（输出）<br>系统允许库存管理人员取消所选中的商品（查询）|
|StorageOverFlowList.Compare|系统将比较实际库存数量和系统数量来决定是否生成库存报溢单（逻辑文件）|
|StorageOverFlowList.Faliure|系统提示无法生成库存报溢单（输出）|
|StorageOverFlowList.Review|系统将生成的库存报溢单提交审批 （逻辑文件）|
|StorageOverFlowList.Update|当库存报溢单获得审批时，系统会更新库存（逻辑文件）|
|StorageOverFlowList.close|当库存管理人员请求结束库存报溢单任务时，系统结束库存报溢单任务|

### 3.2.7 库存报损单
输入:2
输出:3
查询:3
逻辑文件:2
对外接口:0
FP = （2+3+2+3） = 10.6

### 3.2.7.3 相关功能需求
|StorageDamageList |  库存报损单 |
|---|---|
|StorageDamageList.View <br><br> StorageDamageList.View.StorageDamageList |在库存管理人员请求生成库存报损单的时候（查询），系统显示库存中的商品（输出）<br> 系统生成并显示库存报损单（输出）|
|StorageDamageList.Good.Choose <br><br>StorageDamageList.Good.Numbers<br><br> StorageDamageList.Good.enquiry<br><br>StorageDamageList.Good.Invalid <br><br>StorageDamageList.Good.Cancle| 在库存管理人员选择商品时（查询），系统要标识选中的商品并提示输入实际库存数量（输出）<br> 系统应该允许库存管理人员输入商品的实际库存数量数量（输入）<br>系统应该允许库存管理人员输入商品编号、关键字查询商品来选择（输入）<br>当输入的数量多于库存数量或者字符过多，系统提示数量过多或输入负数或字符过多<br>系统允许库存管理人员取消所选中的商品（查询）|
|StorageDamageList.Compare|系统将比较实际库存数量和系统数量来决定是否生成库存报损单（逻辑文件）|
|StorageDamageList.Faliure|系统提示无法生成库存报损单（输出）|
|StorageDamageList.Review|系统将生成的库存报损单提交审批 |
|StorageDamageList.Update|当库存报损单获得审批时，系统会更新库存（逻辑文件）|
|StorageDamageList.close|当库存管理人员请求结束库存报损单任务时，系统结束库存报损单任务|

### 3.2.8 库存报警单
输入:3
输出:4
查询:3
逻辑文件:1
对外接口:0
FP = (3+4+3+1)*1.06 = 11.66

### 3.2.8.3 相关功能需求
|StorageWarningList |  库存报警单 |
|---|---|
|StorageWarningList.View <br><br> StorageWarningList.View.StorageWarningList |在库存管理人员请求生成库存报警单的时候（查询），系统显示库存中的商品（输出）<br> 系统生成并显示库存报警单（输出）|
|StorageWarningList.Good.Choose <br><br>StorageWarningList.Good.Numbers<br><br> StorageWarningList.Good.enquiry<br><br>StorageWarningList.Good.Invalid <br><br>StorageWarningList.Good.Cancle<br>StorageWarningList.Good.Modify| 在库存管理人员选择商品时（查询），系统要标识选中的商品并提示输入实际库存数量（输出）<br> 系统应该允许库存管理人员输入商品的实际库存数量（输入）<br>系统应该允许库存管理人员输入商品编号、关键字查询商品来选择（输入）<br>当输入负数或者字符过多，系统提示输入负数或字符过多（输出）<br>系统允许库存管理人员取消所选中的商品（查询）<br>系统应该允许库存管理人员修改商品的警戒数量（输入）|
|StorageWarningList.Compare|系统将比较实际库存数量和系统数量来决定是否生成库存报警单（逻辑文件）|
|StorageWarningList.Faliure|系统提示无法生成库存报警单|
|StorageWarningList.Review|系统将生成的库存报警单提交审批 |
|StorageWarningList.close|当库存管理人员请求结束库存报警单任务时，系统结束库存报警单任务|






