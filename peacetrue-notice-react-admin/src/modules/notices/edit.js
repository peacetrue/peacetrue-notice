import React from 'react';
import {Edit, required, SimpleForm, NumberInput, TextInput, DateTimeInput, minValue, maxLength} from 'react-admin';

export const NoticeEdit = (props) => {
    console.info('NoticeEdit:', props);
    return (
        <Edit {...props}>
            <SimpleForm>
                <NumberInput source="sourceId" validate={[required(), minValue(0)]} min={0}/>
                <TextInput source="title" validate={[required(), maxLength(255)]}/>
                <TextInput source="content" validate={[required(), maxLength(1024)]}/>
                <TextInput source="stateId" validate={[required(), maxLength(3)]}/>
                <DateTimeInput source="publishedTime" validate={[required(),]}/>
                <NumberInput source="viewCount" validate={[required(), minValue(0)]} min={0}/>
                <TextInput source="remark" validate={[required(), maxLength(255)]}/>
            </SimpleForm>
        </Edit>
    );
};
