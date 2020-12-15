import React from 'react';
import {Datagrid, DateInput, DateField, EditButton, Filter, List, TextField, TextInput} from 'react-admin';

const Filters = (props) => (
    <Filter {...props}>
        <TextInput label={'标题'} source="title" allowEmpty alwaysOn resettable/>
        <TextInput label={'详情'} source="content" allowEmpty alwaysOn resettable/>
        <DateInput label={'发布时间起始值'} source="publishedTime.lowerBound" allowEmpty alwaysOn/>
        <DateInput label={'发布时间结束值'} source="publishedTime.upperBound" allowEmpty alwaysOn/>
        <TextInput label={'备注'} source="remark" allowEmpty alwaysOn resettable/>
        <DateInput label={'创建时间起始值'} source="createdTime.lowerBound" allowEmpty alwaysOn/>
        <DateInput label={'创建时间结束值'} source="createdTime.upperBound" allowEmpty alwaysOn/>
        <DateInput label={'最近修改时间起始值'} source="modifiedTime.lowerBound" allowEmpty alwaysOn/>
        <DateInput label={'最近修改时间结束值'} source="modifiedTime.upperBound" allowEmpty alwaysOn/>
    </Filter>
);

export const NoticeList = props => {
    console.info('NoticeList:', props);
    return (
        <List {...props} filters={<Filters/>}>
            <Datagrid rowClick="show">
                <TextField label={'来源'} source="sourceId"/>
                <TextField label={'标题'} source="title"/>
                <TextField label={'详情'} source="content"/>
                <TextField label={'状态'} source="stateId"/>
                <DateField label={'发布时间'} source="publishedTime" showTime/>
                <TextField label={'浏览次数'} source="viewCount"/>
                <TextField label={'备注'} source="remark"/>
                <TextField label={'创建者主键'} source="creatorId"/>
                <DateField label={'创建时间'} source="createdTime" showTime/>
                <TextField label={'修改者主键'} source="modifierId"/>
                <DateField label={'最近修改时间'} source="modifiedTime" showTime/>
                <EditButton/>
            </Datagrid>
        </List>
    )
};
