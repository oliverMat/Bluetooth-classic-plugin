# bluetooth_classic

- Atualmente o **Flutter** nao possui suporte ao bluetooth sendo necessario escrever codigo nativo, que por sua vez se comunica com o **Dart**, e assim viabiliza a conexão e o envio de dados.
- Pensando em resolver este problema criamos este plugin, de facil manutenção sendo possivel ser implementado em qualquer projeto **Flutter**.

## Platform Support

| Android | iOS | MacOS | Web | Linux | Windows
| :---: | :---: | :---: | :---: | :---: | :---: |
| ✔️ | X | X | X | X | X |

## Getting Started

  1. Adicione isso ao arquivo pubspec.yaml do seu pacote:
````
  bluetooth_classic:
    git:
      url: https://github.com/oliverMat/Bluetooth-classic-plugin.git
````

## BluetoothAdapter Methods


#### Instancia o BluetoothAdapter.

```ruby
   _bluetoothClassicPlugin.initBluetoothAdapter();
```

#### Verifica as permissoes necessarias para a manipulação do bluetooth, retorna Boolean.

```ruby
   _bluetoothClassicPlugin.checkPermission();
```

#### Verifica se o bluetooth esta habilitado, retorna Boolean.

```ruby
   _bluetoothClassicPlugin.isEnableBluetooth();
```

#### Habilita Bluetooth.

```ruby
   _bluetoothClassicPlugin.enableBluetooth();
```

#### Verifica se esta buscando novos aparelhos, retorna Boolean.

```ruby
   _bluetoothClassicPlugin.isDiscoveryDevice();
```

#### Inicia a busca de novos dispositivos.

```ruby
   _bluetoothClassicPlugin.startDeviceDiscovery();
```

#### Interrompe a busca de novos dispositivos.

```ruby
   _bluetoothClassicPlugin.stopDeviceDiscovery();
```

#### Lista os novos dispositivos, retorna um List.

```ruby
   _bluetoothClassicPlugin.listNewDevices();
```

#### Lista os dispositivos pareados, retorna um List.

```ruby
   _bluetoothClassicPlugin.listPairedDevices();
```

#### Chama os dispositivos pareados.

```ruby
   _bluetoothClassicPlugin.callPairedDevices();
```

#### Inicia o recebimento de novos aparelhos.

```ruby
   _bluetoothClassicPlugin.registerBroadcastReceiver();
```

#### Interrompe o recebimento de novos aparelhos.

```ruby
   _bluetoothClassicPlugin.unregisterBroadcastReceiver();
```


## BluetoothSocket Methods

#### Instancia BluetoothSocket, deve ser passado o ``deviceHardwareAddress`` do aparelho selecionado e o ``UUID``.

```ruby
   _bluetoothClassicPlugin.initBluetoothSocket( deviceHardwareAddress , "UUID");
```

#### Verifica se tem algum aparelho conectado, retorna Boolean. 

```ruby
   _bluetoothClassicPlugin.isConnectBluetoothSocket();
```

#### Estabelece conexão com o aparelho selecionado.

```ruby
   _bluetoothClassicPlugin.connectBluetoothSocket();
```

#### Fecha a conexão com o aparelho selecionado.

```ruby
   _bluetoothClassicPlugin.closeBluetoothSocket();
```

#### Envia os dados para o aparelho conectado, deve ser passado em ```Uint8List```

```ruby
   _bluetoothClassicPlugin.outputStreamBluetoothSocket(Uint8List.fromList([0x80, 0x00, 0x00, 0x80]));
```

#### Recebe os dados do aparelho conectado, retorna um ```List<Uint8List>```

```ruby
   _bluetoothClassicPlugin.inputStreamBluetoothSocket()
```
