import React from 'react';
import {Create, maxLength, required, SimpleForm, TextInput} from 'react-admin';
import RichTextInput from 'ra-input-rich-text';
import {toolbarOptions} from "./utils";
export const NoticeCreate = (props) => {
    console.info('NoticeCreate:', props);
    return (
        <Create {...props}>
            <SimpleForm>
                <TextInput source="title" validate={[required(), maxLength(255)]} fullWidth multiline/>
                <RichTextInput source="content"
                               toolbar={toolbarOptions}
                               validate={[required(), maxLength(2048)]}
                />
                <TextInput source="remark" validate={[maxLength(255)]} fullWidth multiline/>
            </SimpleForm>
        </Create>
    );
};
