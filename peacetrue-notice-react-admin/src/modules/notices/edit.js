import React from 'react';
import {DateField, Edit, maxLength, ReferenceField, required, SimpleForm, TextField, TextInput} from 'react-admin';
import PublishTimeField from "./PublishTimeField";
import RichTextInput from "ra-input-rich-text";
import {toolbarOptions} from "./utils";

export const NoticeEdit = (props) => {
    console.info('NoticeEdit:', props);
    return (
        <Edit {...props} undoable={false}>
            <SimpleForm>
                <TextInput source="title" validate={[required(), maxLength(255)]} fullWidth multiline/>
                <RichTextInput source="content" toolbar={toolbarOptions} validate={[required(), maxLength(2048)]}/>
                <TextInput source="remark" validate={[maxLength(255)]} fullWidth multiline/>
                <ReferenceField source="stateId" reference="enums/noticeState" link={false}>
                    <TextField source="name"/>
                </ReferenceField>
                <PublishTimeField withLabel={true}/>
                <TextField source="viewCount"/>
                <ReferenceField reference="users" source="creatorId" link={'show'}>
                    <TextField source="username"/>
                </ReferenceField>
                <DateField source="createdTime" showTime/>
            </SimpleForm>
        </Edit>
    );
};
