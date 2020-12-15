import React from 'react';
import {
    Datagrid,
    DateField,
    DateInput,
    EditButton,
    Filter,
    List,
    ReferenceField,
    ReferenceInput,
    SelectInput,
    TextField,
    TextInput
} from 'react-admin';
import PublishButton from "./PublishButton";
import PublishTimeField from "./PublishTimeField";
import exporterBuilder from "../../exporter";
import {useListStyles} from "../../Styles";
import {ConfirmBulkActionButtons} from "../../Components";

const Filters = (props) => (
    <Filter {...props}>
        <ReferenceInput source="stateId" reference="enums/noticeState" allowEmpty alwaysOn>
            <SelectInput source="name" resettable/>
        </ReferenceInput>
        <TextInput label={'标题'} source="title" allowEmpty alwaysOn resettable/>
        <DateInput label={'发布时间起始值'} source="publishedTime.lowerBound" allowEmpty alwaysOn/>
        <DateInput label={'发布时间结束值'} source="publishedTime.upperBound" allowEmpty alwaysOn/>
    </Filter>
);

export const NoticeList = props => {
    console.info('NoticeList:', props);
    let classes = useListStyles();
    return (
        <List {...props}
              filters={<Filters/>}
              bulkActionButtons={<ConfirmBulkActionButtons/>}
              exporter={exporterBuilder(props.resource)}
        >
            <Datagrid rowClick="show">
                <TextField source="title" cellClassName={classes.width18}/>
                <ReferenceField source="stateId" reference="enums/noticeState" link={false}>
                    <TextField source="name"/>
                </ReferenceField>
                <PublishTimeField label="发布时间"/>
                <TextField source="viewCount"/>
                <ReferenceField reference="users" source="creatorId" link={'show'}>
                    <TextField source="username"/>
                </ReferenceField>
                <DateField source="createdTime" showTime/>
                <EditButton/>
                <PublishButton/>
            </Datagrid>
        </List>
    )
};
