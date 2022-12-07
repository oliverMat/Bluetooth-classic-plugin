import 'package:flutter/material.dart';

class TodoListItem {
  Widget todoListItem(device, onDelete, onEdit) {
    return Padding(
      padding: const EdgeInsets.symmetric(vertical: 2),
      child: Container(
        decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(10),
          color: Colors.grey[200],
        ),
        padding: const EdgeInsets.all(16),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.stretch,
          children: [
            Text(
              device.name,
              style: const TextStyle(fontSize: 16, fontWeight: FontWeight.w600),
            ),
          ],
        ),
      ),
    );
  }
}
