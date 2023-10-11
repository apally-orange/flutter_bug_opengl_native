import 'package:flutter/material.dart';
import 'package:flutter_bug_opengl_native/flutter_bug_opengl_native.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'OpenGL bug',
      home: OpenGLPage(),
    );
  }
}

class OpenGLPage extends StatelessWidget {
  const OpenGLPage({
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('OpenGL Page'),
      ),
      body: Column(
        children: [
          Expanded(
            child: MapViewPlatform.instance.buildView(),
          ),
          SizedBox(height: 5),
          OutlinedButton(
            onPressed: () => MapViewPlatform.instance.test(),
            child: Text("Set Color"),
          ),
        ],
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () => Navigator.push(
          context,
          MaterialPageRoute(builder: (context) => SecondPage()),
        ),
        child: Icon(Icons.arrow_forward),
      ),
    );
  }
}

class SecondPage extends StatelessWidget {
  const SecondPage({
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('SecondPage'),
      ),
      body: Center(
        child: MapViewPlatform.instance.buildView(),
      ),
      floatingActionButtonLocation: FloatingActionButtonLocation.startFloat,
      floatingActionButton: FloatingActionButton(
        onPressed: () => Navigator.pop(context),
        child: Icon(Icons.arrow_back),
      ),
    );
  }
}
