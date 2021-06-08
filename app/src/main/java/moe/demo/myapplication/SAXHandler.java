package moe.demo.myapplication;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXHandler extends DefaultHandler {
    private static final String TAG = "TAG_SAX";
    private String nodeName;
    private StringBuilder id;
    private StringBuilder name;
    private StringBuilder version;

    //开始解析xml时调用
    @Override
    public void startDocument() throws SAXException {
        super.startDocument();

        id = new StringBuilder();
        name = new StringBuilder();
        version = new StringBuilder();
    }

    //..
    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    //开始解析节点时调用
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        nodeName = localName;

    }

    //..
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if ("app".equals(localName)) {
            Log.i(TAG, id.toString().trim() + "");
            Log.i(TAG, name.toString().trim() + "");
            Log.i(TAG, version.toString().trim() +"");
            id.setLength(0);
            name.setLength(0);
            version.setLength(0);
        }

    }

    //获取节点内容时调用
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        if ("id".equals(nodeName)) {
            id.append(ch, start, length);
        }
        else if ("name".equals(nodeName)) {
            name.append(ch, start, length);
        }
        else if ("version".equals(nodeName)) {
            version.append(ch, start, length);
        }
    }


}
