//
//  CourseCollectionViewCell.swift
//  ETSMobile
//
//  Created by Emmanuel Proulx on 2018-12-12.
//  Copyright © 2018 ApplETS. All rights reserved.
//

import Foundation
import UIKit

class CourseCollectionViewCell : UICollectionViewCell {
    @IBOutlet weak var courseTitle: UILabel!
    
    func displayContent(title : String) {
        courseTitle.text = title
    }
}
